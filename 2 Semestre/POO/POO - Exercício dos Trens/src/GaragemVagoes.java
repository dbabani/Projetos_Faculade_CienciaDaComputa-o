import java.util.ArrayList;

public class GaragemVagoes {
    private ArrayList<Vagao> vagoes;

    public GaragemVagoes(){
        vagoes = new ArrayList<>(20);
    }

    public void entra(Vagao vagao){
        vagoes.add(vagao);
    }

    public Vagao sai(int id){
        if(vagoes.size() == 0){
            return null;
        }
        Vagao aux = null;
        for(int i = 0; i<vagoes.size(); i++){
            if(vagoes.get(i).getIdentificador() == id){
                aux = vagoes.get(i);
                vagoes.remove(i);
            }
        }
        return aux;
    }
    public ArrayList<Vagao> getListaVagoes(){
        return vagoes;
    }

    public Vagao getVagao(int ID){
        for(Vagao v:vagoes){
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

        for(Vagao v:vagoes){
            aux += v.toString()+"\n";
        }
        
        return aux;
    }
}
