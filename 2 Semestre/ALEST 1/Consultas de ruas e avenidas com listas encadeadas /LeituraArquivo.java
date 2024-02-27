
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LeituraArquivo {

    public static void main(String[] args) throws ParseException {

        ListaDeRuas listaRua = new ListaDeRuas();
        ListaDeSinalizacoes listaSinal = new ListaDeSinalizacoes();

        String linhas[] = new String[110000];
        int numLinhas = 0;

        Path filePath = Paths.get("dataEditado.csv");
        
        // Ler o arquivo
        try ( BufferedReader reader = Files.newBufferedReader(filePath, Charset.defaultCharset())) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                linhas[numLinhas] = line;
                numLinhas++;
                line = reader.readLine();
                
            }
        } catch (Exception e) {
            System.err.format("Erro na leitura do arquivo: ", e.getMessage());
        }
        
        // Mude numLinhas para algum numero pequeno para executar testes mais rapidamente.
        // Ex:
        // for (int i = 0; i < 50; i++) {
        for (int i = 0; i < 50; i++) {
            String[] campos = linhas[i].split(";");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(campos[0], formatter);
            int anoDataExtracao = dateTime.getYear();
            int mesDataExtracao = dateTime.getMonthValue();
            int diaDataExtracao = dateTime.getDayOfMonth();
            int horaDataExtracao = dateTime.getHour();
            int minDataExtracao = dateTime.getMinute();

            System.out.println("Data e hora extracao: " + diaDataExtracao + "/" + mesDataExtracao + "/" + anoDataExtracao + ", " + horaDataExtracao + ":" + minDataExtracao);

            String descricao = campos[1];
            String estado = campos[2];
            String complemento = campos[3];

            System.out.println("Descricao: " + descricao);
            System.out.println("Estado: " + estado + ", " + complemento);

            int anoImplantacao = 0;
            int mesImplantacao = 0;
            int diaImplantacao = 0;    
            LocalDate date = null;       
            if(!campos[4].equals("")) {
                if(campos[4].contains("-"))
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                else
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(campos[4], formatter);
                anoImplantacao = date.getYear();
                mesImplantacao = date.getMonthValue();
                diaImplantacao = date.getDayOfMonth();
            }
            
            System.out.println("Data implantacao: " + diaImplantacao + "/" + mesImplantacao + "/" + anoImplantacao);

            String logradouro = campos[5].split(" ", 2)[0];
            String nomeLog = campos[5].split(" ", 2)[1];
            System.out.println("Logradouro: " + logradouro + " " + nomeLog);

            double numInicial;
            if(campos[6].equals(""))
                numInicial = 0;
            else
                numInicial = Double.parseDouble(campos[6]);
            
            double numFinal;
            if(campos[7].equals(""))
                numFinal = 0;
            else
                numFinal = Double.parseDouble(campos[7]);
            
            String defronte = campos[8];
            String cruzamento = campos[9];
            String lado = campos[10];
            String fluxo = "";
            if(campos.length>=12)
                fluxo = campos[11];
            String localInstalacao = "";
            if(campos.length>=13)
                localInstalacao = campos[12];

            System.out.println("Num inicial e final: " + numInicial + ", " + numFinal + "; "
                    + defronte + "; " + cruzamento + "; " + lado + "; " + fluxo + "; " + localInstalacao);



            Rua rua = new Rua(nomeLog,logradouro);
            Sinalizacao sinalizacao = new Sinalizacao(descricao, date, numInicial, numFinal, lado, localInstalacao);
            listaRua.insereOrdenado(rua, sinalizacao);

            
            System.out.println("---------------------------------------> " + i);
            System.out.println("\n");
            System.out.println(sinalizacao);
            System.out.println("\n");

        }

        Scanner in = new Scanner(System.in);
        int op;
        listaRua.reset();

        do{
            showMenu();
            op = in.nextInt();
            System.out.println();
            //listaRua.reset();

            switch(op){
                case 1: {
                    System.out.println("Rua com mais sinalizacoes: "+ listaRua.getRuaComMaisSinalizacoes());
                    System.out.println("\n");
                    break;
                }
                case 2: {
                    System.out.println("Mes com mais sinalizacoes implementadas: " + listaRua.getmesmaisSinalizacoes());
                    System.out.println("\n");
                    break;

                }
                case 3: {
                    System.out.println("Voce entrou no modo de navegacao");
                    int opcao;

                    do{
                        System.out.println("Para avancar na lista de ruas , digite 1");
                        System.out.println("Caso queira voltar, digite 2");
                        System.out.println("Caso queira sair do modo de navegacao, digite 0");
                        System.out.print("Escolha: ");
                        opcao = in.nextInt();

                        switch(opcao){
                            case 1: {
                                if (listaRua.next() == null) {
                                    System.out.println("Nao tem mais ruas disponiveis \n");
                                    break;
                                }
                                else {
                                    listaRua.prev();
                                    System.out.println("\n");
                                    System.out.println(listaRua.next());
                                    System.out.println(listaRua.getListaDeSinalizacoes());
                                    System.out.println("\n");
                                    System.out.println(listaRua.getListaDeSinalizacoes().getMaiorData() + "\n");
                                    System.out.println(listaRua.getListaDeSinalizacoes().getMenorData());
                                    System.out.println();
                                    break;
                                }
                            }
                            case 2:{
                                if (listaRua.prev() == null) {
                                    System.out.println("Nao tem mais ruas anteriores disponiveis \n");
                                    break;
                                }
                                else {
                                    listaRua.next();
                                    System.out.println("\n");
                                    System.out.println(listaRua.prev());
                                    System.out.println(listaRua.getListaDeSinalizacoes());
                                    System.out.println("\n");
                                    System.out.println(listaRua.getListaDeSinalizacoes().getMaiorData() + "\n");
                                    System.out.println(listaRua.getListaDeSinalizacoes().getMenorData());
                                    System.out.println();
                                    break;
                                }
                            }
                            case 0: {
                                System.out.println("Saindo do modo de navegacao");
                                System.out.println();
                                break;
                            }
                            default :
                            System.out.println("Opcao invalida");
                        }

                           }while(opcao!= 0);
                        }
                            case 4: {
                                System.out.println("Fim do programa");
                                break;
                            }

                            default:
                            System.out.println("Opcao invalida");
                            break;
                            
                        }
                    }while(op != 4);
                    in.close();
                        
                }

            
     
    public static void showMenu(){
        System.out.println("1 - Apresentar o nome da rua/av/trav que tem mais sinalizações registradas;");
        System.out.println("2 - Apresentar o mês em que mais foram implantadas mais sinalizações em uma rua/av/trav;");
        System.out.println("3 - Modo navegação");
        System.out.println("4 - Finalizar o programa");
        System.out.print("Digite uma opção: ");
        
        }
    



}

    

