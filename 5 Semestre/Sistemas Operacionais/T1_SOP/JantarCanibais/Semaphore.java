package JantarCanibais;

class Semaphore {
    int value;

    public Semaphore(int initial) {
        this.value = initial;
    }

    public synchronized void up() {
        value++;
        notify();
    }

    
}
