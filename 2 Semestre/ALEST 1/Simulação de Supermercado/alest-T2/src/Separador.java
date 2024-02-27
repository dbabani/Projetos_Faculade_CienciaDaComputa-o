public class Separador{
    public enum Status {
        LIVRE, SEPARANDO;
    }
    private Status status;
    private Pedido pedido;
    private Integer pedidos_cont;
    private Integer tempo; // 1 unidade de tempo para cada item

    public Separador() {
        status = Status.LIVRE;
        pedidos_cont = 0;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Pedido getPedido(){
        return pedido;
    }
    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }
    public Integer getTempo() {
        return tempo;
    }
    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }
    public Integer getPedidosCont() {
        return pedidos_cont;
    }
    public void setPedidosCont(Integer pedidos_cont) {
        this.pedidos_cont = pedidos_cont;
    }

    @Override
    public String toString() {
        return  "Separador [status=" + status + "]";
    }
}