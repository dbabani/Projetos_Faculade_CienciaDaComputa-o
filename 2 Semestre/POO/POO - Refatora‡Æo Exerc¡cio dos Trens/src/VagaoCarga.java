public class VagaoCarga implements IntVagao{
    private Trem trem;
    private double capacidade;
    private int identificador;
    
    public VagaoCarga(int identificador, double capacidade) {
        this.identificador = identificador;
        this.trem = null;
        this.capacidade = capacidade;
    }

    @Override
    public int getIdentificador(){
        return identificador;
    }

    @Override
    public void vinculaVagao(Trem refDeTrem){
        trem = refDeTrem;
    }

    
    public double getCapacidade() {
        return capacidade;
    }
    
    @Override
    public String toString() {
        return "VagãoCarga [Identificador = " + identificador + "; Capacidade (Toneladas) = " 
        + capacidade + "; Trem = " + trem + "]";
    }

}
