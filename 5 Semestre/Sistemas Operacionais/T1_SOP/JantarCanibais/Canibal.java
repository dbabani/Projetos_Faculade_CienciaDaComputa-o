package JantarCanibais;

import java.util.Random;

public class Canibal extends Thread {
    private int id;
    private int porcoes;
    private Semaphore mesa;
    private LamportMutex mutex;
    private Random random = new Random();

    
    public Canibal(int id, int porcoes, Semaphore mesa, LamportMutex mutex) {
        this.id = id;
        this.porcoes = porcoes;
        this.mesa = mesa;
        this.mutex = mutex;
    }
    
    @Override
    public void run(){
        while (true){
        try {
            Thread.sleep(3000);
            mutex.lock(id);
                if (mesa.value > 0) {
                    int comida = Math.min(porcoes, mesa.value );
                    System.out.println("Canibal " + id + " pegou  " + comida + "porcoes");   
                    mesa.value = mesa.value - comida; 
                    System.out.println(" Resta " + mesa.value + " porcoes");          
                } else {
                    System.out.println("Acabou a comida!Acorda o cozinheiro");
                    mutex.unlock(id);               
                    continue;
                }
                mutex.unlock(id);

                // Canibal se alimenta
                Thread.sleep(random.nextInt(4000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      }
    }
