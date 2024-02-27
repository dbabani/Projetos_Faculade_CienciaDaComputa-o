public class App {
  public static void calculoDNA(String DNA) {

    StringBuilder sb = new StringBuilder(DNA);
    long tempoInicial = System.nanoTime();
    Character elemento1;
    Character elemento2;
    int count = 0;
    int j = 0;
        
    for (int i = 0; i < Math.pow(sb.length(),1000); i++) {
      count++;

      if ((j + 1) >= sb.length()) {
        break;
      }
            
      if (sb.length() >= 2) {
        elemento1 = sb.charAt(j);
        elemento2 = sb.charAt(j + 1);
      
        if ((elemento1 == 'D' && elemento2 == 'N') || (elemento1 == 'N' && elemento2 == 'D')) {
          sb.deleteCharAt(j);
          sb.deleteCharAt(j);
          sb.append('A');
          if (j != 0) {
            j--;
          }
        } 
        else if ((elemento1 == 'A' && elemento2 == 'N') || (elemento1 == 'N' && elemento2 == 'A')) {
          sb.deleteCharAt(j);
          sb.deleteCharAt(j);
          sb.append('D');
          if (j != 0) {
            j--;
          }
        } 
        else if ((elemento1 == 'D' && elemento2 == 'A') || (elemento1 == 'A' && elemento2 == 'D')) {
          sb.deleteCharAt(j);
          sb.deleteCharAt(j);
          sb.append('N');
          if (j != 0) {
            j--;
          }
        } 
        else if (elemento1 == elemento2) {
          j++;
        }
              
      }
       //System.out.println(sb.toString());
       //System.out.println(j + "\n");
    }
    long tempoFinal = System.nanoTime();
    long tempoDecorrido = tempoFinal - tempoInicial;
    double segundos = (double) tempoDecorrido / 1_000_000_000.0;

    System.out.println("DNA resultante: " + sb.toString());
    System.out.println("Menor tamanho possível: " + sb.length());
    System.out.println("Tempo para concluir: " + segundos + " segundos");
    System.out.println("Número de operações feitas: " + count);
  }
}
