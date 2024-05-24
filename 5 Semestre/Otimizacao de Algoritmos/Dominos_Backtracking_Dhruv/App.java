import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class App {
    private static ArrayList<Domino> firstSolution = null;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Forneça apenas o caminho do arquivo!");
            return;
        }
        printCadeiaDominos(args[0]);
    }

    public static ArrayList<Domino> dominosLista(ArrayList<Domino> cadeiaDominos) {
        int cadeiaSize = cadeiaDominos.size();
        ArrayList<Domino> lista_final = new ArrayList<>();
        dominosLista_Aux(cadeiaDominos, lista_final, cadeiaSize);
        return firstSolution != null ? firstSolution : new ArrayList<Domino>();
    }

    public static void dominosLista_Aux(ArrayList<Domino> cadeiaDominos, ArrayList<Domino> lista, int cadeiaSize) {
        if (firstSolution != null) {
            return; 
        }

        if (lista.size() == cadeiaSize) {
            firstSolution = new ArrayList<>(lista); 
            return;
        }

        for (int i = 0; i < cadeiaDominos.size(); i++) {
            Domino domino = cadeiaDominos.get(i);
            if (possivel(domino, lista)) {
                lista.add(domino);
                ArrayList<Domino> subList = new ArrayList<>(cadeiaDominos);
                subList.remove(i);
                dominosLista_Aux(subList, lista, cadeiaSize);
                if (firstSolution != null) {
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
                if (firstSolution != null) {
                    return; 
                }
                lista.remove(lista.size() - 1);
            }
        }
    }

    public static boolean possivel(Domino domino, ArrayList<Domino> lista) {
        return lista.isEmpty() || lista.get(lista.size() - 1).b == domino.a;
    }

    public static void printCadeiaDominos(String nomeArquivo) {
        try {
            File file = new File(nomeArquivo);
            Scanner fileScanner = new Scanner(file);
            int tamanhoLista = fileScanner.nextInt();
            ArrayList<Domino> dominos = new ArrayList<>();

            for (int i = 0; i < tamanhoLista; i++) {
                dominos.add(new Domino(fileScanner.nextInt(), fileScanner.nextInt()));
            }

            ArrayList<Domino> cadeiaFinal = dominosLista(dominos);
            if (cadeiaFinal.isEmpty()) {
                System.out.println("Nao tem solucao");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Domino d : cadeiaFinal){
                    sb.append(d.toString()).append(" ");
                }
                System.out.println(sb.toString().trim());
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

