package JantarCanibais;

public class LamportMutex {
    private int[] thread;
    

    public LamportMutex(int num_threads) {
        thread = new int[num_threads];
        for (int i = 0; i < num_threads; i++) {
            thread[i] = 0;
        }
        
    }

    public void lock(int thread_id) {
        // Solicita um thread
        thread[thread_id] = max(thread) + 1;

        // Espera até que seja o seu turno
        for (int i = 0; i < thread.length; i++) {
            if (i != thread_id) {
                while (thread[i] != 0 && (thread[i] < thread[thread_id] || (thread[i] == thread[thread_id] && i < thread_id))) {
                    // Espera
                }
            }
        }
    }

    public void unlock(int thread_id) {
        // Libera o recurso
        thread[thread_id] = 0;
    }

    // Função para encontrar o máximo em um array
    private int max(int[] array) {
        int maxVal = Integer.MIN_VALUE;
        for (int val : array) {
            if (val > maxVal) {
                maxVal = val;
            }
        }
        return maxVal;
    }
}
