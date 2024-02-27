import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TesteCaso {
    public static void main(String[] args) {
    
    String[] nomesArquivos = new String[]{
        "Testes/caso10.txt",
        "Testes/caso100.txt",
        "Testes/caso1000.txt",
        "Testes/caso10k.txt",
        "Testes/caso100k.txt",
        "Testes/caso1000k.txt",
        "Testes/caso10000k.txt",
        "Testes/caso30000k.txt",
    };

    for(String nomeArquivo : nomesArquivos){
        lerArquivo(nomeArquivo);
    }
}
    
    public static void lerArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
            App.calculoDNA(linha);
            System.out.println(nomeArquivo + "\n\n");
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
