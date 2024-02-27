import java.util.ArrayList;

public class PatioTrens {
    private ArrayList<Trem> trens;

    public PatioTrens() {
        trens = new ArrayList<>(20);
    }

    public ArrayList<Trem> getListaTrens(){
        return trens;
    }

    public Trem getTrem (int id){
        if(trens.size() == 0){
            return null;
        }
        Trem aux = null;
        for(int i = 0; i<trens.size(); i++){
            if(trens.get(i).getIdentificador()==id){
                aux = trens.get(i);
            }
        }
        return aux;
    }
    public void addTrem(Trem trem){
        trens.add(trem);
    }

    public boolean desfazTrem(int id){

        if(trens.size() == 0){
            return false;
        }
        Trem aux = null;

        for(Trem t:trens){
            if(t.getIdentificador() == id){
                aux = t;
                break;
            }
        }

        for(int i = aux.getQtdeVagoes(); i>0; i--){
            aux.desengataVagao();
        }
        for(int i = aux.getQtdeLocomotivas(); i>0; i--){
            aux.desengataLocomotiva();

            if(aux.getQtdeLocomotivas() == 1){
                aux.desengataLocomotiva();
                aux.getLocomotivaPosicao(0).vinculaVagao(null);
            }
        }
        trens.remove(aux);
        
        return true;
    }
    
    public String toString(){
        String aux = "Pátio dos trens:\n\n";

        if(trens.size() == 0){
            aux += "O pátio dos trens está vazio.";
            return aux;
        }

        for(Trem t:trens){
            aux = aux + t.toString() + "\n";
        }
        return aux;
    }
    
}
