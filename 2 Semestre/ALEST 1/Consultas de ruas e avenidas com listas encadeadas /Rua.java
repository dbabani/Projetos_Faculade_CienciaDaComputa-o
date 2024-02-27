public class Rua{
    private String nomeDaRua;
    private String IdDaRua;


    public String getNomeDaRua() {
        return nomeDaRua;
    }


    public String getIdDaRua() {
        return IdDaRua;
    }


    public Rua(String nomeDaRua, String idDaRua) {
        this.nomeDaRua = nomeDaRua;
        IdDaRua = idDaRua;
    }


    @Override
    public String toString() {
        return IdDaRua + " " + nomeDaRua;
    }


    
}


