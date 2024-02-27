import java.util.ArrayList;

public class Trem {
    private int identificador;
    private ArrayList<IntVagao> vagoes;
    private boolean antesLocomotiva;
    private double pesoMax;
    private int vagoesMax;
    private int passageirosMax;

    public Trem(int identificador, Locomotiva locInicial) {
        this.identificador = identificador;
        this.vagoes = new ArrayList<> (20);
        this.antesLocomotiva = true;
        this.pesoMax = 0.0;
        this.vagoesMax = 0;
        this.passageirosMax = 0;
        engataLocomotiva(locInicial);
    }

    public int getIdentificador() {
        return identificador;
    }

    public boolean getAntesLoc(){
        return antesLocomotiva;
    }

    public ArrayList<IntVagao> getListaVagoes(){
        return vagoes;
    }
    
    public int getQtdeLocomotivas(){  
        int contaLoc = 0;
        for(IntVagao v : vagoes){
            if(v instanceof Locomotiva){
                contaLoc++;
            }

        }  
        return contaLoc;
    }

    public Locomotiva getLocomotivaPosicao(int posicao){

        IntVagao aux = vagoes.get(posicao);

        if(aux instanceof Locomotiva){
            return (Locomotiva) aux;
        }

        return null;
    }

    public int getQtdeVagoes(){
        int contaVag = 0;
        for(IntVagao v : vagoes){
            if(!(v instanceof Locomotiva)){
                contaVag++;
            }

        }  
        return contaVag;
    }

    public boolean engataLocomotiva(Locomotiva locomotiva){
        if(antesLocomotiva == false){//se posição anterior for um vagão, não engata locomotiva
            return false;
        }

        if(locomotiva == null){//se locomotiva não existe, não engata
            return false;
        }
        vagoes.add(locomotiva);//add locomotiva no trem
        
        int vagAux = 0;
        
        for(IntVagao v:vagoes){//pra cada locomotiva do trem, soma o nº de vagões em vagAux
            if(v instanceof Locomotiva){
                Locomotiva aux = (Locomotiva)v;
                vagAux += aux.getNroMaxVagoes();
            }
        }

        vagoesMax = vagAux - ((getQtdeLocomotivas()-1) * vagAux)/10;// novo valor máx de vagoes pro trem
            
        pesoMax += locomotiva.getPesoMaximo();// pesoMax = pesoMax + locomotiva.getPeso.......

        return true;
    }

    public boolean engataVagao (IntVagao vagao){
        if(vagao instanceof VagaoCarga){
            double pesoAux = 0.0;
            VagaoCarga novoCar = (VagaoCarga)vagao;
            for(IntVagao v:vagoes){
                if(v instanceof VagaoCarga){
                    VagaoCarga aux = (VagaoCarga)v;
                    pesoAux += aux.getCapacidade();
                }
            }

            if(vagoesMax == getQtdeVagoes() || pesoAux + novoCar.getCapacidade() > pesoMax){
                return false;
            }
        }
            else{
                VagaoPassageiros novoPas = (VagaoPassageiros)vagao;
                passageirosMax += novoPas.getCapacidadePas();
            }

        vagoes.add(vagao);
        
        antesLocomotiva = false;

        return true;
    }

    public boolean desengataLocomotiva(){
        if(getQtdeVagoes()!=0 || getQtdeLocomotivas()<=1){
            return false;
        }
        Locomotiva aux = (Locomotiva)vagoes.get(getQtdeLocomotivas()-1);

        pesoMax -= aux.getPesoMaximo();

        vagoes.remove(aux);

        aux.vinculaVagao(null);

        int vagAux = 0;
        for(IntVagao v:vagoes){//pra cada locomotiva do trem, soma o nº de vagões em vagAux
            if(v instanceof Locomotiva){
                Locomotiva aux2 = (Locomotiva)v;
                vagAux += aux2.getNroMaxVagoes();
            }
        }
        vagoesMax = vagAux - ((getQtdeLocomotivas()-1) * vagAux)/10;
        
        return true;
    }

    public boolean desengataVagao(){
        if(getQtdeVagoes() == 0){
            return false;
        }
        if(getQtdeVagoes() == 1){
            antesLocomotiva = true;
        }
        IntVagao aux = vagoes.get(vagoes.size()-1);

        if(aux instanceof VagaoPassageiros){
            VagaoPassageiros auxPas = (VagaoPassageiros)aux;
            passageirosMax -= auxPas.getCapacidadePas();
        }
            
        vagoes.remove(aux);
        
        aux.vinculaVagao(null);

        return true;
    }

    public IntVagao getUltVagao(){
        if(getQtdeVagoes() == 0){
            return null;
        }

        return vagoes.get(vagoes.size()-1);
    }

    public Locomotiva getUltLocomotiva(){
        if(getQtdeLocomotivas() == 0){
            return null;
        }

        return (Locomotiva)vagoes.get(getQtdeLocomotivas()-1);
    }
    public String toString(){
        String loc = "";
        String vag = "";

        if(getQtdeLocomotivas()== 0){
            loc = "Sem locomotivas"; 
        }   
        else{
            for(IntVagao l: vagoes){
                if(l instanceof Locomotiva){
                    loc = loc + "Locomotiva ["+ l.getIdentificador()+ "]; ";
                }
                    else{
                        break;
                    }
                
            }
        }

        if(getQtdeVagoes() == 0){
            vag = "Sem vagões";
        }
        else{
            for(IntVagao v: vagoes){
                if(v instanceof VagaoCarga){
                    vag = vag + "VagãoCarga ["+ v.getIdentificador()+ "]; ";
                }
                    else if (v instanceof VagaoPassageiros){
                        vag = vag + "VagãoPassageiros ["+ v.getIdentificador()+ "]; ";
                    }
            }
        }
        
        return "Trem [Identificador = " + identificador +"; Peso máximo (Toneladas) = " +pesoMax + "; Nº máximo de passageiros = "+ 
        passageirosMax + "; Nº máximo de vagões = " + vagoesMax +";\n      Locomotivas = " + loc + "\n      Vagões = "+
         vag +"\n]\n";
    }
}
