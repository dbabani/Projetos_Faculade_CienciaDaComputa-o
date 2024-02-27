/*
 *    T.10 POO - Refatoração do Exercício dos Trens
 * 
 * Autores: Dhurv Babani, João Pedro Sostruznik Sotero da Cunha, Matheus Gonzaga Krebs e Vítor Aguirre Caús
 * 
 * Informações para execução do programa estão presentes no arquivo read.me
 * 
 */

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        GaragemLocomotivas gl = new GaragemLocomotivas();
        GaragemVagoes gv = new GaragemVagoes();
        PatioTrens patio = new PatioTrens();
        Scanner in = new Scanner(System.in);

        //variáveis auxiliares
        boolean loop1 = true;//menu inicial
        boolean loop2 = true;//menu edição
        boolean insereAux = false;
        Trem tremAux = null;
        Locomotiva locomotivaAux = null;
        IntVagao vagaoAux = null;
        int idAux = 0;

        //Locomotivas e Vagões previamente criados
        gl.entra(new Locomotiva(414, 140, 50));
        gl.entra(new Locomotiva(112, 130.5, 40));
        gl.entra(new Locomotiva(741, 135, 45));
        gl.entra(new Locomotiva(596, 165, 70));
        gl.entra(new Locomotiva(283, 150.5, 55));
        gl.entra(new Locomotiva(654, 170, 60));


        gv.entra(new VagaoCarga(375, 50.5));
        gv.entra(new VagaoCarga(928, 75.5));
        gv.entra(new VagaoCarga(129, 60));
        gv.entra(new VagaoCarga(640, 80.5));
        gv.entra(new VagaoCarga(257, 90));
        gv.entra(new VagaoCarga(763, 55));

        gv.entra(new VagaoPassageiros(683, 50));
        gv.entra(new VagaoPassageiros(852, 70));
        gv.entra(new VagaoPassageiros(198, 60));
        gv.entra(new VagaoPassageiros(386, 80));
        gv.entra(new VagaoPassageiros(307, 100));
        gv.entra(new VagaoPassageiros(404, 60));


        do{//menu inicial
            System.out.println("\n----------Sistema de Composição de Trens----------");
            System.out.println("\nInsira o número correspondente à ação que deseja realizar:");
            System.out.println("1 - Criar um trem");
            System.out.println("2 - Editar um trem");
            System.out.println("3 - Listar os trens já criados");
            System.out.println("4 - Desfazer um trem");
            System.out.println("5 - Sair");
            
            switch(in.nextInt()){
                case 1://criar trem
                    if(gl.getListaLocomotiva().size() == 0){
                        System.out.println("Não existem locomotivas disponíveis para criar um trem."+
                        "\nRemova locomotivas de trens já existentes para criar um novo.");
                        break;
                    }
                    System.out.println("Insira um número de identificação para o trem:");
                    idAux = in.nextInt();
                    
                    System.out.println("Insira o identificador da locomotiva que deseja inserir no início do trem:\n");
                    System.out.println(gl.toString());
                    locomotivaAux = gl.sai(in.nextInt());

                    if(locomotivaAux == null){
                        System.out.println("A locomotiva com o identificador inserido não existe. Tente novamente.");
                        break;
                    }
                    tremAux = new Trem(idAux, locomotivaAux);
                    patio.addTrem(tremAux);
                    locomotivaAux.vinculaVagao(tremAux);
                    
                    System.out.println("\nTrem criado com sucesso. Volte ao menu e escolha 'Editar' para continuar sua construção.");
                    break;
                
                case 2://editar trem
                    loop2 = true;
                    if(patio.getListaTrens().size() == 0){
                        System.out.println("Não existem trens no pátio. Crie um trem para poder acessar o sistema de edição.");
                    }
                        else{
                            System.out.println("Insira o número identificador do trem que a ser editado:\n");
                            
                            tremAux = patio.getTrem(in.nextInt());

                            if(tremAux == null){
                                System.out.println("O trem com o identificador inserido não existe. Tente novamente");
                                break;
                            }

                            do{//Menu de edição
                            System.out.println("\n----------Menu de Edição de Trens----------"+ 
                            "\n\nInsira o número correspondente à ação que deseja realizar:");

                            System.out.println("1 - Inserir uma locomotiva");
                            System.out.println("2 - Inserir um vagão");
                            System.out.println("3 - Remover último elemento do trem");
                            System.out.println("4 - Listar locomotivas livres");
                            System.out.println("5 - Listar vagões livres");
                            System.out.println("6 - Encerrar edição");

                            switch(in.nextInt()){
                                case 1://insere locomotiva
                                    if(gl.getListaLocomotiva().size() == 0){
                                        System.out.println("Não existem locomotivas disponíveis."+
                                        "\nRemova locomotivas de um trem existente para liberá-las.");
                                        break;
                                    }
                                    if(tremAux.getAntesLoc() == false){
                                        System.out.println("Não é possível inserir uma locomotiva, "+
                                        "pois um vagão está presente na posição anterior.");
                                    }
                                        else{
                                            System.out.println("Insira o número identificador da locomotiva:");
                                            idAux = in.nextInt();
                                            locomotivaAux = gl.getLocomotiva(idAux);

                                            if(locomotivaAux == null){
                                                System.out.println("A locomotiva com o identificador inserido não existe. Tente novamente.");
                                                break;
                                            }
                                            
                                            insereAux = tremAux.engataLocomotiva(locomotivaAux);

                                            if(insereAux == false){
                                                System.out.println("Não foi possível inserir a locomotiva."+
                                                " Verifique se um vagão está na posição anterior ou se o identificador está incorreto.");
                                            }
                                               else{
                                                    gl.sai(idAux);
                                                    locomotivaAux.vinculaVagao(tremAux);
                                                    System.out.println("Locomotiva inserida com sucesso!");
                                                }
                                        }
                                    break;

                                case 2://insere vagão
                                    if(gv.getListaVagoes().size() == 0){
                                        System.out.println("Não existem vagões disponíveis."+ 
                                        "\nRemova vagões de um trem para liberá-los.");
                                        break;
                                    }
                                    System.out.println("Insira o número identificador do vagão:");
                                    idAux = in.nextInt();
                                    vagaoAux = gv.getVagao(idAux);

                                    if(vagaoAux == null){
                                        System.out.println("O vagão com o identificador inserido não existe. Tente novamente.");
                                        break;
                                    }
                                    
                                    insereAux = tremAux.engataVagao(vagaoAux);

                                    if(insereAux == false){
                                        System.out.println("Não foi possível inserir o vagão. Verifique se os limites de peso e/ou vagões foram ultrapassados.");
                                    }
                                        else{
                                            gv.sai(idAux);
                                            vagaoAux.vinculaVagao(tremAux);
                                            System.out.println("Vagão inserido com sucesso");
                                        }
                
                                    break;

                                case 3://tirar último elemento
                                    if(tremAux.getQtdeVagoes() > 0){

                                        vagaoAux = tremAux.getUltVagao();
                                        tremAux.desengataVagao();
                                        gv.entra(vagaoAux);

                                        System.out.println("Último elemento (Vagão) removido");
                                    }
                                        else{
                                            if(tremAux.getQtdeLocomotivas()>1){

                                                locomotivaAux = tremAux.getUltLocomotiva();
                                                tremAux.desengataLocomotiva();
                                                gl.entra(locomotivaAux);

                                                System.out.println("Último elemento (Locomotiva) removido");
                                            }
                                                else{
                                                    System.out.println("O último elemento é a única locomotiva do trem. Deve-se "+
                                                    "desfazer o trem para removê-la.");
                                                }
                                        }
                                    break;

                                case 4://mostrar locomotivas livres
                                    System.out.println(gl.toString());
                                    break;

                                case 5://mostrar vagões livres
                                    System.out.println(gv.toString());
                                    break;

                                case 6://sair
                                    System.out.println("Edição de trem encerrada.");
                                    loop2 = false;
                                    break;

                                default:
                                    System.out.println("Ação inválida! Tente novamente");
                                    System.out.println("Insira qualquer coisa para confirmar que leu esta mensagem:");
                                    in.next();
                            }
                            }while(loop2);
                        }
                    break;
                case 3://listar pátio
                    System.out.println(patio.toString());

                    break;

                case 4://desfazer trem
                    int contaVag = 0;//conta vagoes direcionados pra garagem de vagões
                    if(patio.getListaTrens().size() == 0){
                        
                        System.out.println("Não existem trens no pátio."+
                        "\nCrie trens para que possa desfazê-los.");
                        break;
                    }

                    System.out.println("Insira o número identificador do trem a ser desfeito:");
                    idAux = in.nextInt();
                    Trem auxTrem = patio.getTrem(idAux);

                    if(auxTrem == null){
                        System.out.println("O trem com o identificador inserido não existe. Tente novamente.");
                        break;
                    }

                    while(auxTrem.getQtdeVagoes() != contaVag){
                        for(IntVagao v: auxTrem.getListaVagoes()){
                            if(!(v instanceof Locomotiva)){
                                gv.entra(v);
                                contaVag++;
                            }
                        }

                    }

                    for(IntVagao v: auxTrem.getListaVagoes()){
                        if(v instanceof Locomotiva){
                            gl.entra((Locomotiva)v);
                        }
                    }

                    patio.desfazTrem(idAux);

                    System.out.println("Trem desfeito com sucesso. Suas locomotivas e vagões retornaram às garagens.");
                    
                    break;
                
                case 5://sair
                    System.out.println("Execução encerrada");
                    loop1 = false;
                    break;
                
                default:
                    System.out.println("Ação inválida! Tente novamente");
                    System.out.println("Insira qualquer coisa para confirmar que leu esta mensagem:");
                    in.next();
            }
        }while(loop1);
    }
}