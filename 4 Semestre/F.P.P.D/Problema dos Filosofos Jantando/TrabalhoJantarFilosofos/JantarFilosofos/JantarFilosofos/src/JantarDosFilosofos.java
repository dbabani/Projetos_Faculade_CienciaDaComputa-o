import java.util.concurrent.Semaphore;

public class JantarDosFilosofos {

    private static final int N = 5; // Número de filósofos

    public static void main(String[] args) {
        // Cada garfo será um semáforo
        Semaphore[] garfo = new Semaphore[N];

        for (int i = 0; i < N; i++) {
            garfo[i] = new Semaphore(1);
        }

        // Cria os filósofos e inicia cada um executando a sua thread
        Filosofos[] filosofo = new Filosofos[N];
        for (int i = 0; i < N; i++) {
			long start = System.currentTimeMillis();
            filosofo[i] = new Filosofos(i, garfo[i], garfo[(i + 1) % N]);
            new Thread(filosofo[i]).start();
			long end = System.currentTimeMillis() - start;
			System.out.println(end);
        }

		

        
}
}
