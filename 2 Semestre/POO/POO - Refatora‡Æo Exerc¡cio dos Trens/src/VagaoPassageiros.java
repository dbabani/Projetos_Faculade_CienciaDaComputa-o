public class VagaoPassageiros implements IntVagao {
    private Trem trem;
    private int capacidade;
    private int identificador;

    public VagaoPassageiros(int identificador, int capacidade) {
        this.capacidade = capacidade;
        this.identificador = identificador;
    }

    @Override
    public int getIdentificador(){
        return identificador;
    }

    @Override
    public void vinculaVagao(Trem refDeTrem){
        trem = refDeTrem;
    }

    
    public int getCapacidadePas(){
        return capacidade;
    }
    
    @Override
    public String toString() {
        return "VagãoPassageiros [Identificador = " + identificador + "; Capacidade (Pessoas)  = " 
        + capacidade + "; Trem = " + trem + "]";
    }
    
}
