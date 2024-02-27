import java.util.Scanner;

public class Ackermann {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println(
                "Programa Ackermann\n Digite os parâmetros m e n para calcular A(m, n) ou -1 para abortar a execução");

        int m = scan.nextInt();

        if (!(m < 0)) {
            int n = scan.nextInt();
            int resultado = ackermann(m, n);
            System.out.println("resposta: " + resultado);
        }
        else{
            System.out.println("Execução encerrada");
        }
        scan.close();
    }

    public static int ackermann(int m, int n) {
        return ackermannRec(m, n);
    }

    private static int ackermannRec(int m, int n) {
        if (m == 0)
            return n + 1;

        else if (m > 0 && n == 0)
            return ackermannRec(m - 1, 1);

        else
            return ackermannRec(m - 1, ackermannRec(m, n - 1));
    }

}
