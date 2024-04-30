import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class App{
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Forneça apenas o caminho do arquivo!");
            return;
        }
        printCadeiaDominos(args[0]);
    }

    public static ArrayList<Domino>  dominosLista (ArrayList<Domino> cadeiaDominos){
        int cadeiaSize = cadeiaDominos.size();
        ArrayList<Domino>  lista_final = new ArrayList<>();
        dominosLista_Aux(cadeiaDominos, lista_final, cadeiaSize);
        return lista_final;
    }

    public static void dominosLista_Aux(ArrayList<Domino> cadeiaDominos, ArrayList<Domino> lista, int cadeiaSize) {
        if (lista.size() == cadeiaSize) {
            return;
        }
        
        for (int i = 0; i < cadeiaDominos.size(); i++) {
            Domino domino = cadeiaDominos.get(i);
            if (possivel(domino, lista)) {
                lista.add(domino);
                ArrayList<Domino> subList = new ArrayList<>(cadeiaDominos);
                subList.remove(i);
                dominosLista_Aux(subList, lista, cadeiaSize);
                if (lista.size() == cadeiaSize) {
                    return;
                }
                lista.remove(lista.size() - 1);
            }
    
            domino = domino.flipped();
            if (possivel(domino, lista)) {
                lista.add(domino);
                ArrayList<Domino> subList = new ArrayList<>(cadeiaDominos);
                subList.remove(i);
                dominosLista_Aux(subList, lista, cadeiaSize);
                if (lista.size() == cadeiaSize) {
                    return;
                }
                lista.remove(lista.size() - 1);
            }
        }
    }
    

    public static boolean possivel(Domino domino,ArrayList<Domino>  lista){
        return lista.isEmpty() || lista.get(lista.size()- 1).b == domino.a;
    }

    public static void printCadeiaDominos(String nomeArquivo){
        try {
            File file = new File(nomeArquivo);
            Scanner fileScanner = new Scanner(file);
            int tamanhoLista = fileScanner.nextInt();
            ArrayList<Domino> dominos = new ArrayList<>();

            for (int i = 0; i < tamanhoLista; i++) {
                dominos.add(new Domino(fileScanner.nextInt(), fileScanner.nextInt()));
            }

            ArrayList<Domino> cadeiaFinal = dominosLista(dominos);
            if(cadeiaFinal == null){
                System.out.println("Nao tem solucao");
            }

            System.out.println(cadeiaFinal);
            
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
    
        }
        
    }
}