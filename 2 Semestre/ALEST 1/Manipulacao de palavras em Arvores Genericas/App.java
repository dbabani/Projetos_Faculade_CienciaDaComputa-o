import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        LinkedList<Palavra> lista = new LinkedList<>();
        String aux[];
        WordTree tree = new WordTree();

        Path path1 = Paths.get("dicionario.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {// Charset.defaultCharset())
            String line = reader.readLine();
            while (line != null) {
                aux = line.split(";");
                if(lista.size()==0) {
                    aux[0] = aux[0].substring(1);
                    //System.out.println(aux[0]);
                }
                Palavra p = new Palavra(aux[0],aux[1]);
                lista.add(p);
                line = reader.readLine();
                tree.addWord(p);
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        //Inicio do programa
        Scanner in = new Scanner(System.in);
        int op;

        do {
            showMenu();
            op = in.nextInt();

            switch (op) {
                case 1:{
                    System.out.print("Digite o prefixo: ");
                    String opcao;
                    opcao = in.next();
                    System.out.println();
                    System.out.println("Palavras com o prefixo: " + tree.searchAll(opcao));
                    System.out.print("\nDigite a palavra que deseja ver o significado: ");
                    String opcao2;
                    opcao2 = in.next();
                    System.out.println(tree.searchAllSignificado(opcao2) + "\n\n");
                    break;
                }
                case 2:{
                    System.out.println("Obrigado por usar o programa!");
                    in.close();
                    System.exit(0);
                }
            
                default:
                    System.out.println("Opcao invalida" + "/n");
                    break;
            }
        } while (op != 2);
    }

     public static void showMenu(){
        System.out.println("-----------MENU-------------");
        System.out.println("1 - Inicializar o dicionario");
        System.out.println("2 - Finalizar o programa");
        System.out.print("Digite uma opção: ");
        
    }
 
}