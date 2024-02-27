public class Vagao {
    private int identificador;
    private double capacidade;
    private Trem trem;

    public Vagao(int identificador, double capacidade) {
        this.identificador = identificador;
        this.capacidade = capacidade;
        this.trem = null;
    }

    public int getIdentificador() {
        return identificador;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public Trem getTrem() {
        return trem;
    }

    public void vinculaVagao(Trem refDeTrem){
        trem = refDeTrem;
    }
    
    @Override
    public String toString() {
        return "Vagão [Identificador = " + identificador + "; Capacidade (Toneladas) = " 
        + capacidade + "; Trem = " + trem + "]";
    }

    
    
}
