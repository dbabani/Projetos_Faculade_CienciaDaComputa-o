package JantarCanibais;


public class Cozinheiro extends Thread{
    private Semaphore mesa;
    private int porcoes;


    public Cozinheiro(Semaphore mesa,int porcoes){
        this.mesa = mesa;
        this.porcoes=porcoes - 1;

    }
    
    @Override
    public void run(){
        while (true) {
            try {
                
                while (mesa.value > 0) {
                    Thread.sleep(1000);
                }
                
                 
                if(mesa.value == 0){
                // Cozinheiro acorda e prepara mais porções
                System.out.println("\nCozinheiro:vou preparar a comida.");
                mesa.value = porcoes;
                System.out.println("Comida pronta! Acordem canibais!");
                
                } 
                // Notifica os canibais que mais porções estão disponíveis
                mesa.up();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
