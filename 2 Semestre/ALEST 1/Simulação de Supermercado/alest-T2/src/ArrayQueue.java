public class ArrayQueue {

    private Pedido [] queue;
    private int inicio, fim, nElementos;

    public ArrayQueue(int size){
        if(size <=1)
            size=10;
        queue = new Pedido[size];
        nElementos=inicio=fim=0;
    }

    public Pedido[] getQueue(){
        return queue;
    }

    public void enqueue(Pedido value){
        if(nElementos>=queue.length)
            grow();
        else if(fim>=queue.length)
            shiftQueue();
    

        queue[fim]=value;
        fim++;
        nElementos++;
    }

    private void shiftQueue(){
        for(int i=0; i<nElementos; i++)
          queue[i]=queue[i+inicio];
        inicio=0;
        fim=nElementos;
    }

    private void grow(){
        Pedido[] newQueue = new Pedido[queue.length*2];
        for(int i=0; i<queue.length; i++)
          newQueue[i]=queue[i];
        queue=newQueue;
    }

    public Pedido dequeue() throws Exception{
        if(nElementos>0){
            nElementos--;
            inicio++;
            return queue[inicio-1];
        }
        else
            throw new Exception("Fila vazia");
    }

    public Pedido head() throws Exception{
        if(nElementos>0)
            return queue[inicio];
        else
            throw new Exception("A fila está vazia");
    }

    public boolean isEmpty(){
        return(nElementos==0);
    }

    public void clear(){
        queue = new Pedido[10];
        nElementos=inicio=fim=0;
    }
    public int size(){
        return nElementos;
    }
    
}