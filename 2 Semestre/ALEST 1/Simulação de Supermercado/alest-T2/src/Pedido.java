public class Pedido{
    public enum Status {
        A_SER_SEPARADO, SEPARANDO, A_SER_ENTREGUE, A_CAMINHO, ENTREGUE, CANCELADO, QUASE_CANCELADO;
    }
    private Status status;
    private Integer numero;
    private Integer quantidade_itens;

    public Pedido() {
        status = Status.A_SER_SEPARADO;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public Integer getQuantidade_itens() {
        return quantidade_itens;
    }
    public void setQuantidade_itens(Integer quantidade_itens) {
        this.quantidade_itens = quantidade_itens;
    }

    @Override
    public String toString() {
        return  "[numero=" + numero + ", status=" + status + ", quantidade_itens=" + quantidade_itens + "]";
    }
    }

        
    