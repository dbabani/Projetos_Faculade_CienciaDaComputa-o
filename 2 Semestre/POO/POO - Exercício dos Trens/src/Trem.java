import java.util.ArrayList;

public class Trem {// adicionar max vagoes tbm
    private int identificador;
    private ArrayList<Locomotiva> locomotivas;
    private ArrayList<Vagao> vagoes;
    private boolean antesLocomotiva;
    private double pesoMax;
    private int vagoesMax;

    public Trem(int identificador, Locomotiva locInicial) {
        this.identificador = identificador;
        this.locomotivas = new ArrayList<> (20);
        this.vagoes = new ArrayList<> (20);
        this.antesLocomotiva = true;
        this.pesoMax = 0.0;
        this.vagoesMax = 0;
        engataLocomotiva(locInicial);
    }

    public int getIdentificador() {
        return identificador;
    }

    public boolean getAntesLoc(){
        return antesLocomotiva;
    }

    public ArrayList<Vagao> getListaVagoes(){
        return vagoes;
    }

    public ArrayList<Locomotiva> getListaLocomotivas(){
        return locomotivas;
    }

    public int getQtdeLocomotivas(){    
        return locomotivas.size();
    }

    public Locomotiva getLocomotivaPosicao(int posicao){
        return locomotivas.get(posicao);
    }

    public int getQtdeVagoes(){
        return vagoes.size();
    }

    public boolean engataLocomotiva(Locomotiva locomotiva){
        if(antesLocomotiva == false){//se posição anterior for um vagão, não engata locomotiva
            return false;
        }

        if(locomotiva == null){//se locomotiva não existe, não engata
            return false;
        }
        locomotivas.add(locomotiva);//add locomotiva no trem
        
        int vagAux = 0;
        
        for(Locomotiva l:locomotivas){//pra cada locomotiva do trem, soma o nº de vagões em vagAux
            vagAux += l.getNroMaxVagoes();
        }

        vagoesMax = vagAux - ((locomotivas.size()-1) * vagAux)/10;// novo valor máx de vagoes pro trem
            
        pesoMax += locomotiva.getPesoMaximo();// pesoMax = pesoMax + locomotiva.getPeso.......

        return true;
    }

    public boolean engataVagao (Vagao vagao){
        if(antesLocomotiva){
            antesLocomotiva = false;
        }
        
        double pesoAux = 0.0;
        for(Vagao v:vagoes){
            pesoAux += v.getCapacidade();
        }
        if(vagoesMax == vagoes.size() || pesoAux+vagao.getCapacidade() > pesoMax){
            return false;
        }

        vagoes.add(vagao);

        return true;
    }

    public boolean desengataLocomotiva(){
        if(vagoes.size()!=0 || locomotivas.size()<=1){
            return false;
        }
        Locomotiva aux = locomotivas.get(locomotivas.size()-1);
        locomotivas.remove(aux);

        aux.vinculaLocomotiva(null);
        
        return true;
    }

    public boolean desengataVagao(){
        if(vagoes.size() == 0){
            return false;
        }
        if(vagoes.size() == 1){
            antesLocomotiva = true;
        }
        Vagao aux = vagoes.get(vagoes.size()-1);
        vagoes.remove(aux);
        
        aux.vinculaVagao(null);

        return true;
    }

    public Vagao getUltVagao(){
        if(vagoes.size() == 0){
            return null;
        }

        return vagoes.get(vagoes.size()-1);
    }

    public Locomotiva getUltLocomotiva(){
        if(locomotivas.size() == 0){
            return null;
        }

        return locomotivas.get(locomotivas.size()-1);
    }
    public String toString(){
        String loc = "";
        String vag = "";

        if(locomotivas.size ()== 0){
            loc = "Sem locomotivas"; 
        }   
        else{
            for(Locomotiva l: locomotivas){
                loc = loc + "Locomotiva ["+ l.getIdentificador()+ "]; ";
                
            }
        }

        if(vagoes.size() == 0){
            vag = "Sem vagões";
        }
        else{
            for(Vagao v: vagoes){
                vag = vag + "Vagão ["+ v.getIdentificador()+ "]; ";
            }
        }
        
        return "Trem [Identificador = " + identificador +"; Peso máximo (Toneladas) = " +pesoMax +"; Nº máximo de vagões = "
        + vagoesMax +";\n      Locomotivas = " + loc + "\n      Vagões = "+ vag +"]\n";
    }
}
