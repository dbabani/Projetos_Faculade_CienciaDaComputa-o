import java.time.LocalDate;

public class Sinalizacao {
    private String descricao;
    private LocalDate implantacao;
    private double numInicial;
    private double numFinal;
    private String lado;
    private String localDeInstalacao;


    public Sinalizacao(String descricao, LocalDate implantacao, double numInicial, double numFinal, String lado,
        String localDeInstalacao) {
        this.descricao = descricao;
        this.implantacao = implantacao;
        this.numInicial = numInicial;
        this.numFinal = numFinal;
        this.lado = lado;
        this.localDeInstalacao = localDeInstalacao;
    }

    public LocalDate getImplantacao() {
        return implantacao;
    }
    @Override
    public String toString() {

        int ano, mes, dia;
        ano = implantacao.getYear();
        mes = implantacao.getMonthValue();
        dia = implantacao.getDayOfMonth();
        

       
        return  " Sinalizacao: "  +    descricao + ", " + "Data implantacao: " + dia + "/" + mes + "/" + ano + 
        "; Num inicial e final: " + numInicial + ", " + numFinal + "; " + lado + "; " + localDeInstalacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getNumInicial() {
        return numInicial;
    }

    public double getNumFinal() {
        return numFinal;
    }

    public String getLado() {
        return lado;
    }

    public String getLocalDeInstalacao() {
        return localDeInstalacao;
    }



    
}
