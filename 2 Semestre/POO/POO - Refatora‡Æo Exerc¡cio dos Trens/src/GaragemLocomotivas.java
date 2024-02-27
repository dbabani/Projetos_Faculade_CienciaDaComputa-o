import java.util.ArrayList;

public class GaragemLocomotivas{
    private ArrayList<Locomotiva> locomotivas;

    public GaragemLocomotivas(){
        locomotivas = new ArrayList<>(10);
    }

    public void entra(Locomotiva locomotiva){
        locomotivas.add(locomotiva);
    }

    public Locomotiva sai(int id){
        int count = 0;
        
        for(Locomotiva l:locomotivas){
            if(l.getIdentificador() != id){
                count++;
            }

        }

        if(count == locomotivas.size()){
            return null;
        }

        if(locomotivas.size() == 0){
            return null;
        }

        Locomotiva aux = null;
        
        for(int i = 0; i<locomotivas.size(); i++){
            if(locomotivas.get(i).getIdentificador() == id){
                aux = locomotivas.get(i);
                locomotivas.remove(i);
            }
        }
        return aux;
        
    }

    public ArrayList<Locomotiva> getListaLocomotiva(){
        return locomotivas;
    }

    public Locomotiva getLocomotiva(int ID){
        for(Locomotiva l:locomotivas){
            if(l.getIdentificador() == ID){
                return l;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        String aux = "Garagem das locomotivas:\n\n";
        
        if(locomotivas.size() == 0){
            aux += "A garagem de locomotivas está vazia.";
            return aux;
        }

        for(Locomotiva l:locomotivas){
            aux += l.toString()+"\n";
        }
        
        return aux;
    }
}