package main

import (
	"crypto/md5"
	"fmt"
	"math/rand"
	"os"
	"sync"
	"time"
)

var (
	mutex            sync.Mutex
	chans            = []chan election_message{make(chan election_message), make(chan election_message), make(chan election_message), make(chan election_message)}
	control          = make(chan int)
	wg               sync.WaitGroup
	expected_payload = []byte("Test")
)

type election_message struct {
	purpose              int
	pids                 [4]int
	index                int
	elected              int
	commander            int
	dead_at_gathering    int
	dead_at_confirmation int
	payload              []byte
	joker_id             int
}

func ElectionControler(in chan int, commander int, dead_at_gathering int, dead_at_confirmation int, payload []byte, joker_id int) {

	// Injeta uma mensagem de eleicao e aguarda o retorno do novo eleito
	// Eleicao consiste em duas etapas: 1 -> coleta de prioridades, 2 -> anuncio do novo eleito,
	// Se a etapa de coleta for bem sucedida, controle recebe 0, caso contrario, -1
	// Se a etapa de anuncio do novo eleito for bem sucedida, controle recebe o id do novo eleito, caso contrario, -1

	defer wg.Done()

	var em election_message

	em.purpose = 1
	em.pids[0] = -1
	em.pids[1] = -1
	em.pids[2] = -1
	em.pids[3] = -1
	em.index = 0
	em.elected = -1
	em.commander = commander
	em.dead_at_gathering = dead_at_gathering
	em.dead_at_confirmation = dead_at_confirmation
	em.payload = payload
	em.joker_id = joker_id

	fmt.Printf("Control Process says: \"Commander is %d.\"\n", em.commander)

	if dead_at_gathering == em.commander {
		fmt.Printf("Control Process says: \"Commander cannot be dead at gathering!\" \n")
		os.Exit(0)
	}

	chans[3] <- em // pedir eleicao para o processo 0
	fmt.Printf("Control Process says: \"Election message sent!\" \n")

	gathering_result := <-in
	if gathering_result == 0 {
		fmt.Printf("Control Process says: \"All PIds gathered. Waiting for confirmation...\"\n")
		confirmation_result := <-in
		payload_checksum := <-in
		if confirmation_result > 0 {
			fmt.Printf("Control Process says: \"Election confirmed. Elected PId: %d.\"\n", confirmation_result)
		} else {
			fmt.Printf("Control Process says: \"Election failed in confirmation\"\n")
		}

		if payload_checksum == 1 {
			fmt.Printf("Control Process says: \"Payload checksum operated successfully!\"\n")
		} else {
			fmt.Printf("Control Process says: \"Payload checksum failed!\"\n")
		}
	} else {
		fmt.Printf("Control Process says: \"Failed in gathering all PIds\"\n")
	}
}

func ElectionStage(PId int, in chan election_message, out chan election_message) {
	defer wg.Done()
	select {
	case pids_collect := <-in:
		if pids_collect.dead_at_gathering != PId {

			fmt.Printf("%2d says: \"I've received a message of purpose %d: [ %d, %d, %d, %d ]\" \n", PId, pids_collect.purpose, pids_collect.pids[0], pids_collect.pids[1], pids_collect.pids[2], pids_collect.pids[3])
			pids_collect.pids[pids_collect.index] = PId
			pids_collect.index += 1

			if pids_collect.joker_id == PId {
				pids_collect.payload = []byte("Hahaha!")
			}

			out <- pids_collect
			fmt.Printf("%2d says: \"I've sent a message to the next node\" \n", PId)

			select {
			case confirmation_msg := <-in:

				fmt.Printf("%2d says: \"I've received a message of purpose %d, [ %d, %d, %d, %d ]\"\n", PId, confirmation_msg.purpose, confirmation_msg.pids[0], confirmation_msg.pids[1], confirmation_msg.pids[2], confirmation_msg.pids[3])

				if pids_collect.commander == PId {
					control <- 0 // Success

					fmt.Printf("%2d says: \"I've gathered all PIds and sent confirmation to control\" \n", PId)

					var elected = -1
					for i := 0; i < 4; i++ {
						if confirmation_msg.pids[i] > elected {
							elected = confirmation_msg.pids[i]
						}
					}

					confirmation_msg.purpose = 2
					confirmation_msg.elected = elected

					if confirmation_msg.elected == PId {
						fmt.Printf("%2d says: \"Oh yeeah! I'm the elected node!\"\n", PId)
					}

					out <- confirmation_msg

					fmt.Printf("%2d says: \"I've sent foward the elected node for the ring\" \n", PId)

					select {
					case finish_msg := <-in:
						control <- finish_msg.elected // Elected node PId
						fmt.Printf("%2d says: \"Everyone knows about the new elected. I've sent a successfull confirmation to control.\" \n", PId)

						expected_checksum := md5.Sum([]byte(expected_payload))
						current_checksum := md5.Sum([]byte(finish_msg.payload))

						payload_checksum := expected_checksum == current_checksum

						fmt.Printf("%2d says: \"I'm executing the payload checksum, expected: %x current: %x.\"\n", PId, expected_checksum, current_checksum)

						if payload_checksum {
							control <- 1
						} else {
							control <- 0
						}
					case <-time.After(5 * time.Second):
						control <- -1
						fmt.Printf("%2d says: \"I've just reported to Control that someone died in election confirmation.\"\n", PId)
					}
				} else {
					if pids_collect.dead_at_confirmation != PId {
						fmt.Printf("%2d says: \"I've just received the new elected node: %d\"\n", PId, confirmation_msg.elected)
						if confirmation_msg.elected == PId {
							fmt.Printf("%2d says: \"Oh yeeah! I'm the elected node!\"\n", PId)
						}
						out <- confirmation_msg
						fmt.Printf("%2d says: \"I've sent forward who is the elected node.\"\n", PId)
					}
				}
			case <-time.After(5 * time.Second):
				if pids_collect.commander == PId {
					control <- -1
					fmt.Printf("%2d says: \"I've just reported to Control that someone died in PId gathering.\"\n", PId)
				}
			}
		}
	case <-time.After(5 * time.Second):
	}
	fmt.Printf("%2d says: \"My job is done here. Later!\"\n", PId)
}

func main() {

	wg.Add(5)

	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	id_1, id_2, id_3, id_4 := 0, 0, 0, 0

	// Avoid same ids
	for {
		id_1, id_2, id_3, id_4 = r.Intn(1000), r.Intn(1000), r.Intn(1000), r.Intn(1000)
		if id_1 != id_2 && id_1 != id_3 && id_1 != id_4 && id_2 != id_3 && id_2 != id_4 && id_3 != id_4 {
			break
		}
	}

	go ElectionStage(id_1, chans[3], chans[0])
	go ElectionStage(id_2, chans[0], chans[1])
	go ElectionStage(id_3, chans[1], chans[2])
	go ElectionStage(id_4, chans[2], chans[3])

	fmt.Println("\n   Anel de processos criado")

	go ElectionControler(control, id_1, -1, -1, expected_payload, -1)

	fmt.Print("\n   Processo controlador criado\n\n")

	wg.Wait()
}
