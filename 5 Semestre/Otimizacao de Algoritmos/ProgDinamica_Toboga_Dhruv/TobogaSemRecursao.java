import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TobogaSemRecursao {
    public static int[][] matriz;

    public static void main(String[] args) {
        System.out.println("Numero de caminhos possiveis sem recursão:" + contadorCaminhosPossiveis_Iterativo(args[0]));
        
    }
    public static long contadorCaminhosPossiveis_Iterativo_Aux(int[][] matriz) {
        int rows = matriz.length;
        int colunas = matriz[0].length;        
        long[][] caminhos = new long[rows][colunas];
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] == 1) {
                    caminhos[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    caminhos[i][j] = 1;
                } else {
                    long caminhoEsquerda = (j > 0) ? caminhos[i][j - 1] : 0;
                    long caminhoDiagonal = (i > 0 && j > 0) ? caminhos[i - 1][j - 1] : 0;
                    caminhos[i][j] = caminhoEsquerda + caminhoDiagonal;
                }
            }
        }
    
        return caminhos[rows - 1][colunas - 1];
    }

    public static long contadorCaminhosPossiveis_Iterativo(String nomeArquivo) {
        try {
            File file = new File(nomeArquivo);
            Scanner fileScanner = new Scanner(file);
            int rows = fileScanner.nextInt();
            int colunas = fileScanner.nextInt();
            int[][] matriz = new int[rows][colunas];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colunas; j++) {
                    matriz[i][j] = fileScanner.nextInt();
                }
            }

            // Sem Recursão
            long caminhosPossiveis_Iterativo = contadorCaminhosPossiveis_Iterativo_Aux(matriz);

            fileScanner.close(); 

            return caminhosPossiveis_Iterativo;
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return 0; 
        }
    }  
}
