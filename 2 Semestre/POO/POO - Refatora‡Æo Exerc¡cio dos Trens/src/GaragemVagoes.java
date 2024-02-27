import java.util.ArrayList;

public class GaragemVagoes {
    private ArrayList<IntVagao> vagoes;

    public GaragemVagoes(){
        vagoes = new ArrayList<>(15);
    }

    public void entra(IntVagao vagao){
        vagoes.add(vagao);
    }

    public IntVagao sai(int id){
        if(vagoes.size() == 0){
            return null;
        }
        IntVagao aux = null;
        for(int i = 0; i<vagoes.size(); i++){
            if(vagoes.get(i).getIdentificador() == id){
                aux = vagoes.get(i);
                vagoes.remove(i);
            }
        }
        return aux;
    }
    public ArrayList<IntVagao> getListaVagoes(){
        return vagoes;
    }

    public IntVagao getVagao(int ID){
        for(IntVagao v:vagoes){
            if(v.getIdentificador() == ID){
                return v;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        String aux = "Garagem dos vagões:\n\n";

        if(vagoes.size() == 0){
            aux += "A garagem de vagões está vazia.";
            return aux;
        }

        for(IntVagao v:vagoes){
            aux += v.toString()+"\n";
        }
        
        return aux;
    }
}
