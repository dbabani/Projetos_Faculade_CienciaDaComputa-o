public class Entregador{
    public enum Status {
        LIVRE, ENTREGANDO;
    }
    private Status status;
    private Pedido pedido;
    private Integer PedidosCont;
    
    
    private Integer rodadas_retorno; // rodadas para retorno a liberação

    public Entregador() {
        status = Status.LIVRE;
        PedidosCont = 0;

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
    public Integer getRodadas_retorno() {
        return rodadas_retorno;
    }
    public void setRodadas_retorno(Integer rodadas_retorno) {
        this.rodadas_retorno = rodadas_retorno;
    }
    public Integer getPedidosCont() {
        return PedidosCont;
    }

    public void setPedidosCont(Integer pedidosCont) {
        PedidosCont = pedidosCont;
    }

    
    


    @Override
    public String toString() {
        return  "Entregador [status=" + status + "]";
    }
}