import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Contribuicoes: Lorenzo Windmoller e Alisson Carnetti
public class App {

    static List<List<Double>> A = new ArrayList<>();
    static List<Integer> b = new ArrayList<>();
    static Double[] valor_x;
    static int iteracoes = 0;

    public static void main(String[] args) {
        List<List<Integer>> dados = lerArquivo("caso010.txt");
        montarSistema(dados);
        resolverSistema(0.001, 100);
        System.out.println("Número de iterações realizadas:");
        System.out.println(iteracoes);
        double maisFofocas = maximo(valor_x);
        int velhinhaMaisFofocas = indiceMaximo(valor_x) + 1;
        System.out.println("A velhinha que terá recebido mais fofocas durante o dia é a velhinha número:");
        System.out.println(velhinhaMaisFofocas);
        System.out.println("Ela terá recebido aproximadamente:");
        System.out.println(maisFofocas);
    }

    public static List<List<Integer>> lerArquivo(String nomeArquivo) {
        List<List<Integer>> dados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(":");
                List<Integer> amigos = new ArrayList<>();
                for (String amigo : partes[1].split("\\s+")) {
                    if (!amigo.isEmpty()) { 
                        amigos.add(Integer.parseInt(amigo));
                    }
                }
                dados.add(amigos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }
    

    public static void montarSistema(List<List<Integer>> dados) {
        int numero_Velhinhas = dados.size();
        
        for (int i = 1; i < numero_Velhinhas + 1 ; i++) {
            List<Double> linha = new ArrayList<>();
            int termo = 0;
            
            // Percorrer cada velhinha do sistema
            for (int j = 1; j < numero_Velhinhas + 1; j++) {
                double coeficiente;
                if (i == j) {
                    coeficiente = 1.0;
                }
                else {
                    coeficiente = probabilidadeContar(j, i, dados);
                }
                linha.add(coeficiente);
            }
            termo = 1;
            b.add(termo);
            A.add(linha);
        }
        
        
    }

    public static double probabilidadeContar(int velhinha, int amiga, List<List<Integer>> dados) {
        int numero_amigas = dados.get(velhinha - 1).size();
        double chance_contar = (1 - 0.1) / numero_amigas;
        if(dados.get(velhinha - 1).contains(amiga)){
            return chance_contar;

        }else{
            return 0.0;
        }
    }


    public static void resolverSistema(double tol, int maxIter) {
        int n = A.size();
        valor_x = new Double[n];

        for(int i = 0;i< valor_x.length;i++)
             valor_x[i] = 0.0; 
        int iter = 0;
        double erro = tol + 1;
        while (erro > tol && iter < maxIter) {
            iter++;
            erro = 0;
            Double[] xCopia = Arrays.copyOf(valor_x, n);
            for (int i = 0; i < n; i++) {
                double soma = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        soma += A.get(i).get(j) * xCopia[j];
                    }
                }
                double xNovo = (b.get(i) + soma) / A.get(i).get(i);
                double erroI = Math.abs(xNovo - xCopia[i]);
                if (erroI > erro) {
                    erro = erroI;
                }
                valor_x[i] = xNovo;
            }
        }
        iteracoes = iter;
    }

    public static double maximo(Double[] vetor) {
        double max = vetor[0];
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > max) {
                max = vetor[i];
            }
        }
        return max;
    }

    public static int indiceMaximo(Double[] vetor) {
        double max = vetor[0];
        int indice = 0;
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > max) {
                max = vetor[i];
                indice = i;
            }
        }
        return indice;
    }
}
