public class App {

    public static void main(String[] args) {
        Grafo g = new Grafo("Codigo/CasosDeTeste/casoteste.txt");

        long tempoInicial = System.nanoTime();
        System.out.println("Numero de Hidrogenios:" + g.calcularHidrogenio("hidrogenio"));
        long tempoFinal = System.nanoTime();

        long tempoDecorrido = tempoFinal - tempoInicial;
        double segundos = (double) tempoDecorrido / 1_000_000_000.0;
        System.out.println("Tempo(s):" + segundos);

    }
}