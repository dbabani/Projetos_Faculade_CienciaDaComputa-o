import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TobogaMem {

    public static int[][] matriz;

    public static void main(String[] args) {
        System.out.println("Numero de caminhos possiveis com Memorização:" + contadorCaminhosPossiveis_Recursivo_Memorizacao(args[0]));
    }

    public static long contadorCaminhosPossiveis_Recursivo_Memorizacao_Aux(int[][] matriz, int rows, int colunas, long[][] Mem) {
        if (rows < 0 || colunas < 0) {return 0; }
        if (colunas == 0 && rows > 0) {return 0;}
        if (matriz[rows][colunas] == 1) {return 0;}
        if (rows == 0 && colunas == 0) {return 1;}
        if (Mem[rows][colunas] != -1) {return Mem[rows][colunas];}
    
        long caminhos_Possiveis = 0;
        caminhos_Possiveis = contadorCaminhosPossiveis_Recursivo_Memorizacao_Aux(matriz, rows, colunas - 1, Mem) + contadorCaminhosPossiveis_Recursivo_Memorizacao_Aux(matriz, rows - 1, colunas - 1, Mem);
    
        Mem[rows][colunas] = caminhos_Possiveis; // Guardando o resultado da função na memória.
        return caminhos_Possiveis;
    }

    public static long contadorCaminhosPossiveis_Recursivo_Memorizacao(String nomeArquivo) {
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

            long[][] Mem = new long[rows][colunas];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colunas; j++) {
                    Mem[i][j] = -1;
                }
            }

            // Recursivo com Memorização:
            long caminhosPossiveis_Recursivo_Mem = contadorCaminhosPossiveis_Recursivo_Memorizacao_Aux(matriz, rows - 1, colunas - 1,Mem);

            fileScanner.close(); 

            return caminhosPossiveis_Recursivo_Mem;
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return 0; 
        }
    }  
    
}
