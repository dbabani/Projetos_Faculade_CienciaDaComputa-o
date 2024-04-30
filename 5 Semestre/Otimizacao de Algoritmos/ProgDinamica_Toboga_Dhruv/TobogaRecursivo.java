import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TobogaRecursivo {
    public static int[][] matriz;

    public static void main(String[] args) {
        System.out.println("Numero de caminhos possiveis com recursão:" + contadorCaminhosPossiveis_Recursivo(args[0]));
    }


    public static long contadorCaminhosPossiveis_Recursivo_Aux(int[][] matriz, int rows, int colunas) {
        if(rows < 0 || colunas < 0){return 0;}
        if(colunas == 0 && rows > 0){return 0;}
        if(matriz[rows][colunas] == 1){return 0;}
        if (rows == 0 && colunas == 0){return 1;}

        return contadorCaminhosPossiveis_Recursivo_Aux(matriz, rows, colunas - 1) + contadorCaminhosPossiveis_Recursivo_Aux(matriz, rows - 1, colunas - 1);  
    }

    public static long contadorCaminhosPossiveis_Recursivo(String nomeArquivo) {
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

            // Recursivo
            long caminhosPossiveis_Recursivo = contadorCaminhosPossiveis_Recursivo_Aux(matriz, rows - 1, colunas - 1);

            fileScanner.close(); 

            return caminhosPossiveis_Recursivo;
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return 0; 
        }
    }  
}
