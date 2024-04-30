package JantarCanibais;


public class App {
    public static void main(String[] args) throws InterruptedException {
        // Definindo os parâmetros
        int canibais = 4; // Número de canibais
        int porcoes = 10; // Capacidade inicial da travessa
        System.out.println("Numero de canibais:" + canibais + " " + "Quantidade de Comida:" + porcoes);



        // Inicializando semáforo contador para a mesa
        Semaphore mesa = new Semaphore(porcoes);

        // Inicializando mutex
        LamportMutex mutex = new LamportMutex(canibais);

        // Criando threads para os canibais e o cozinheiro
        Canibal[] cannibals = new Canibal[canibais];

        for (int i = 0; i < canibais; i++) {
            cannibals[i] = new Canibal(i, (int) (Math.random() * 3) + 1, mesa, mutex);
            cannibals[i].start();
        }

        
        Cozinheiro cozinheiro = new Cozinheiro(mesa,porcoes);
        cozinheiro.start();



    }
}