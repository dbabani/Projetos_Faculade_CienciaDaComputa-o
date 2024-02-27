public class Locomotiva {
    private int identificador;
    private double pesoMaximo;
    private int nroMaxVagoes;
    private Trem trem;

    public Locomotiva(int identificador, double pesoMaximo, int nroMaxVagoes) {
        this.identificador = identificador;
        this.pesoMaximo = pesoMaximo;
        this.nroMaxVagoes = nroMaxVagoes;
        this.trem = null;
    }

    public int getIdentificador() {
        return identificador;
    }

    public double getPesoMaximo() {
        return pesoMaximo;
    }

    public int getNroMaxVagoes() {
        return nroMaxVagoes;
    }

    public void vinculaLocomotiva(Trem trem){
        this.trem = trem;
    }

    @Override
    public String toString() {
        return "Locomotiva [Identificador = " + identificador + "; Nº Máximo Vagões = " + nroMaxVagoes 
        + "; Peso Máximo = " + pesoMaximo + "; Trem = " + trem + "]";
    }
}
