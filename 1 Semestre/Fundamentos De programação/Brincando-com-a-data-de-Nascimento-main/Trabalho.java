import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Trabalho {
    public static void main(String[] args) {
        
        Scanner leia = new Scanner(System.in);
        String data;
        String[] dataSeparada;
        LocalDateTime nascimento, momentoAtual;
        
        System.out.printf("\f");
        System.out.println("Bem vindo ao Brincando com sua Data de Nascimento! Escolha um número correspondente para continuar");
           
        
        for(int i = 0; i < 1000; i++){ //Criamos um loop para quando o programa responder o usuário, ele volte para o menu 
            System.out.printf("\n");
            System.out.println("1. Descobrir quantos dias, horas e minutos você já viveu");
            System.out.println("2. Descobrir quanto tempo falta para o seu próximo aniversário");
            System.out.println("3. Descobrir o dia da semana que você nasceu");
            System.out.println("4. Sair do programa");
            int resposta = leia.nextInt();
            
            leia.nextLine();
            
            switch(resposta){
                case 1:
                    System.out.println("Digite sua data de nascimento no formato dia/mes/ano/horas/minutos: ");
                    data = leia.nextLine();
        
                    dataSeparada = data.split("/");
                    nascimento = LocalDateTime.of(Integer.parseInt(dataSeparada[2]), 
                    Integer.parseInt(dataSeparada[1]), 
                    Integer.parseInt(dataSeparada[0]), 
                    Integer.parseInt(dataSeparada[3]), 
                    Integer.parseInt(dataSeparada[4]), 0);
        
                    momentoAtual = LocalDateTime.now();
        
                    long minutos = nascimento.until(momentoAtual, ChronoUnit.MINUTES);
                    long horas   = nascimento.until(momentoAtual, ChronoUnit.HOURS);
                    long dias    = nascimento.until(momentoAtual, ChronoUnit.DAYS);
            
                    System.out.printf("\nVocê já viveu %d dias, ", dias);
                    System.out.printf("%d horas e ", (horas % 24));
                    System.out.printf("%d minutos.\n", (minutos % 60));
                    break;
                case 2:
                    System.out.println("Digite sua data de nascimento no formato dia/mes/ano/horas/minutos: ");
                    data = leia.nextLine();
    
                    dataSeparada = data.split("/");
                    nascimento = LocalDateTime.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]), Integer.parseInt(dataSeparada[3]), Integer.parseInt(dataSeparada[4]), 0);
    
                    momentoAtual = LocalDateTime.now();
                
                    int diaNascimento = nascimento.getDayOfMonth();
                    int diaAtual      = momentoAtual.getDayOfMonth();
                    int mesNascimento = nascimento.getMonthValue();
                    int mesAtual      = momentoAtual.getMonthValue();
                    int anoAtual      = momentoAtual.getYear();
                    
                    if (diaNascimento == diaAtual && mesNascimento == mesAtual) {
                        System.out.println(" O seu aniversário é hoje. Parabéns!.");
                    } else {
                        long diasAteAniversario;
                        if (mesNascimento > mesAtual || (mesNascimento == mesAtual && diaNascimento > diaAtual)) {
                            LocalDateTime aniversario = LocalDateTime.of(anoAtual, mesNascimento, diaNascimento, 23, 59);
                            diasAteAniversario = momentoAtual.until(aniversario, ChronoUnit.DAYS);
                        } else {
                            LocalDateTime aniversario = LocalDateTime.of((anoAtual + 1), mesNascimento, diaNascimento, 23, 59);
                            diasAteAniversario = momentoAtual.until(aniversario, ChronoUnit.DAYS);
                        }
                        System.out.printf("Falta(m) %d dias para seu aniversário.", diasAteAniversario);
                    }
                    break;
                case 3: 
                    System.out.println("Digite sua data de nascimento no formato (dia/mes/ano)");
                     data = leia.next();//lendo a data do usuario
                    momentoAtual = LocalDateTime.now();
                     dataSeparada = data.split("/");
                
                    int dia = Integer.parseInt(dataSeparada[0]);
                    int mes = Integer.parseInt(dataSeparada[1]);
                    int ano = Integer.parseInt(dataSeparada[2]);
                    System.out.println(dia + "/" + mes + "/"+ ano);
                    //Verificacao das tres principais variaveis:o dia,mes e o ano
                 
                    if((dia <= 31 && dia != 0) && (mes <= 12 && mes != 0) && (ano >= 1900 && ano <= momentoAtual.getYear())){
                        //inicio do algoritmo de zeller(Achar o dia da semana)
                   
                        if(mes == 1){
                            mes = 13;
                            ano= ano- 1;
                        }
                        if (mes== 2)
                        {
                            mes = 14;
                            ano= ano - 1;
                        }
                        int h= dia + 13*(mes + 1) / 5 + (ano%100) + (ano%100)/4+(ano/100)/4+5*(ano/100);
                        h = h % 7;
                        
                    
                        
                
                        
                        //Calculando o resultado do algoritmo e logo abaixo imprimimdo o dia da semana correspondido
                
                        switch(h){
                            case 0:
                                System.out.println("sabado");
                                break;
                            case 1:
                                System.out.println("domingo");
                                break;
                            case 2:
                                System.out.println("segunda");
                                break;
                            case 3:
                                System.out.println("terca")
                                ;break;
                            case 4:
                                System.out.println("quarta");
                                break;
                            case 5:
                                System.out.println("Quinta");
                                break;
                            case 6:
                                System.out.println("sexta");
                                break;
                        }
                    }
                    break;
                    
                    
                    
                case 4:
                    i = 1000;
                    System.out.println("Obrigado por usar o Brincando com sua data de Nascimento.");
                    break; 
                default:
                    System.out.println("Você não selecionou uma opção válida. Obrigado por utilizar o programa");                     
                    break;
            }
        } 
        leia.close();  
    }
}

