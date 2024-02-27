import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Integer NUMERO_MAX_PEDIDOS = 100;
    private static final Integer NUMERO_MAX_ITENS = 50;
    private static Integer pedidos_cont = 0;
    private static Integer pedidos_cancelados_rodada = 0;
    private static Integer pedidos_confirmado_cont = 0;
    private static Integer pedidos_sucedidos_cont = 0;
    private static Integer pedidos_quasecancelados_cont = 0;
    private static Integer pedidos_cancelados_cont = 0;
    private static Integer rodadas_filaColeta_cont = 0;
    private static Integer rodadas_filaEntrega_cont = 0;
    private static ArrayQueue pedidosColeta;
    private static ArrayQueue  pedidosEntrega;
    private static Pedido pedido_ultimo_atendido = null;


    private static Random random = new Random();

    public static Entregador[] entregador = new Entregador[3];
    public static Separador[] separador = new Separador[3];

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int numero_pedidos = random.nextInt(NUMERO_MAX_PEDIDOS) + 1;
        pedidosColeta = new ArrayQueue(numero_pedidos);
        pedidosEntrega = new ArrayQueue(numero_pedidos);

        System.out.print("Insira o numero de rodadas:");
        int rodadas = sc.nextInt();

        GerarPedidos(numero_pedidos);

        GerarSeparadores();
        GerarEntregadores();

        while (rodadas > 0) {
            pedidos_cancelados_rodada = 0;

            SepararPedidos();
            EntregarPedidos();

            rodadas--;

        }
        
        Integer max_separador_cont = 0;
        Integer max_separador_cont_index = 0;

        for (int i = 0; i < separador.length; i++) {
            if (separador[i].getPedidosCont() > max_separador_cont) {
                max_separador_cont = separador[i].getPedidosCont();
                 max_separador_cont_index = i + 1;
                }
            }

            Integer max_entregador_cont = 0;
            Integer max_entregador_cont_index = 0;

        for (int j = 0; j < entregador.length; j++) {
            if (entregador[j].getPedidosCont() > max_entregador_cont) {
                max_entregador_cont = entregador[j].getPedidosCont();
                max_entregador_cont_index = j + 1;
        }
            }
         
       System.out.println();

    
        System.out.println("Teve entrada de " + pedidos_confirmado_cont +    " pedidos ");
        System.out.println("Apenas "   +      pedidos_sucedidos_cont  +                " pedidos foram bem sucedidos");
        System.out.println("Apenas "+  pedidos_quasecancelados_cont +        " pedidos foram quase cancelados");
        System.out.println("Apenas "+    pedidos_cancelados_cont + " pedidos foram cancelados");
        System.out.println("Separador "+  max_separador_cont_index + " separou mais");
        System.out.println("Entregador "+ max_entregador_cont_index + " entregou mais");
        System.out.println("o tempo médio de cada pedido na fila de coleta foi "+   rodadas_filaColeta_cont/pedidos_confirmado_cont +  " ciclos");
        System.out.println("o tempo médio de cada pedido na fila de entrega foi "+  rodadas_filaEntrega_cont/pedidos_confirmado_cont +    " ciclos");
        
        String pedido_numero = pedido_ultimo_atendido != null ? pedido_ultimo_atendido.getNumero().toString():" ";
        
        System.out.println("o pedido " + pedido_numero + " foi o que levou mais tempo para ser atendido"); 
        



        sc.close();
    }

    private static void ImprimeEstadoPedido(Pedido pedido) {
        System.out.printf("Pedido numero %d - status alterado para %s\n", pedido.getNumero(), pedido.getStatus().toString());
    }

    private static void ImprimeEstadoSeparador(int index, Separador separador) {
        System.out.printf("Separador numero %d - status alterado para %s\n", index + 1, separador.getStatus().toString());
    }
    
    private static void ImprimeEstadoEntregador(int index, Entregador entregador) {
        System.out.printf("Entregador numero %d - status alterado para %s\n", index + 1, entregador.getStatus().toString());
    }

    private static void GerarPedidos(int numero_pedidos) {
        for (int i = 0; i < numero_pedidos; i++) {
            Pedido pedido = new Pedido();

            pedido.setNumero(++pedidos_cont);
            pedido.setQuantidade_itens(random.nextInt(NUMERO_MAX_ITENS));
            
            int porcentagem_pedido_confirmado = random.nextInt(100);

            if (porcentagem_pedido_confirmado <= 95) {
                pedido.setStatus(Pedido.Status.A_SER_SEPARADO);
                pedidos_confirmado_cont++;
                

                ImprimeEstadoPedido(pedido);
            } else {
                pedido = null;
                
            }
            
            pedidosColeta.enqueue(pedido);

            
        }
        
    }

    private static void GerarSeparadores() {
        for(int i = 0;i<separador.length;i++){
            separador[i] = new Separador();
        }
    }

    private static void GerarEntregadores() {
        for(int i = 0;i<entregador.length;i++){
            entregador[i] = new Entregador();
        }
    }

    private static void SepararPedidos() throws Exception {
        

        for(int j = 0; j < separador.length;j++ ){
            if(separador[j].getStatus() == Separador.Status.LIVRE) { 
                if (pedidosColeta.size() > 0) SeparadorLivre(j, separador[j]);
                rodadas_filaColeta_cont++;
            } else {
                SeparadorOcupado(j, separador[j]);
            }
        }

        
    }

    private static void EntregarPedidos() throws Exception {
        for(int j = 0; j < entregador.length; j++) {
            if (entregador[j].getStatus() == Entregador.Status.LIVRE) {
                if (pedidosEntrega.size() > 0) EntregadorLivre(j, entregador[j]);
                rodadas_filaEntrega_cont++;
            } else {
                EntregadorOcupado(j, entregador[j]);
            }
        }
        
          
    }
    
    private static void SeparadorLivre(int index, Separador separador) throws Exception {
        Pedido pedido = pedidosColeta.dequeue();
        


        if(pedido != null){  
            int porcentagem_pedido_cancelado = random.nextInt(100);

            if (porcentagem_pedido_cancelado <= 5 && pedido.getStatus() != Pedido.Status.CANCELADO && pedidos_cancelados_rodada == 0) {
                pedido.setStatus(Pedido.Status.CANCELADO);
                pedidos_cancelados_cont++;

                pedidos_cancelados_rodada++;

                ImprimeEstadoPedido(pedido);
                

            } else {
                pedido.setStatus(Pedido.Status.SEPARANDO);
                pedido_ultimo_atendido = pedido;

                ImprimeEstadoPedido(pedido);

                separador.setStatus(Separador.Status.SEPARANDO);
                separador.setPedido(pedido);
                separador.setTempo(pedido.getQuantidade_itens());

                ImprimeEstadoSeparador(index, separador);
            }
        }
        

    }

    private static void SeparadorOcupado(int index, Separador separador) {
        int porcentagem_pedido_cancelado = random.nextInt(100);

        if (porcentagem_pedido_cancelado <= 5 && separador.getPedido().getStatus() != Pedido.Status.CANCELADO && pedidos_cancelados_rodada == 0) {
            separador.getPedido().setStatus(Pedido.Status.CANCELADO);
            pedidos_cancelados_cont++;

            pedidos_cancelados_rodada++;

            ImprimeEstadoPedido(separador.getPedido());

            separador.setStatus(Separador.Status.LIVRE);
            separador.setPedidosCont(separador.getPedidosCont() + 1);
            separador.setPedido(null);
            separador.setTempo(0);

            ImprimeEstadoSeparador(index, separador);
        } else {
            if (separador.getTempo() >= 10) {
                separador.setTempo(separador.getTempo() - 1);
            } else {
                Pedido pedido = separador.getPedido();

                pedido.setStatus(Pedido.Status.A_SER_ENTREGUE);

                ImprimeEstadoPedido(separador.getPedido());

                pedidosEntrega.enqueue(pedido);

                separador.setStatus(Separador.Status.LIVRE);
                separador.setPedido(null);
                separador.setTempo(0);

                ImprimeEstadoSeparador(index, separador);
            }
        }
    }

    private static void EntregadorLivre(int index, Entregador entregador) throws Exception {
        Pedido pedido = pedidosEntrega.dequeue();
        

                            
        int porcentagem_pedido_cancelado = random.nextInt(100);

        if (porcentagem_pedido_cancelado <= 5 && pedido.getStatus() != Pedido.Status.CANCELADO && pedidos_cancelados_rodada == 0) {
            pedido.setStatus(Pedido.Status.CANCELADO);
            pedidos_cancelados_rodada++;
            pedidos_cancelados_cont++;


            ImprimeEstadoPedido(pedido);
            
        } else {
            pedido.setStatus(Pedido.Status.A_CAMINHO);
            
            ImprimeEstadoPedido(pedido);
    
            entregador.setStatus(Entregador.Status.ENTREGANDO);
            entregador.setPedido(pedido);

            int numero_rodadas_retorno = random.nextInt(5) + 4;

            entregador.setRodadas_retorno(numero_rodadas_retorno);

            ImprimeEstadoEntregador(index, entregador);
        }
        

    }

    private static void EntregadorOcupado(int index, Entregador entregador) {
        int porcentagem_pedido_cancelado = random.nextInt(100);

        if(porcentagem_pedido_cancelado <= 5 && entregador.getPedido().getStatus() != Pedido.Status.CANCELADO) {
            

            entregador.getPedido().setStatus(Pedido.Status.QUASE_CANCELADO);
            pedidos_quasecancelados_cont++;

            ImprimeEstadoPedido(entregador.getPedido());
        } else {
            entregador.setRodadas_retorno(entregador.getRodadas_retorno() - 1);

            if (entregador.getRodadas_retorno() == 0) {
                entregador.getPedido().setStatus(Pedido.Status.ENTREGUE);
                pedidos_sucedidos_cont++;

                ImprimeEstadoPedido(entregador.getPedido());

                entregador.setStatus(Entregador.Status.LIVRE);
                entregador.setPedidosCont(entregador.getPedidosCont() + 1);

                entregador.setPedido(null);

                ImprimeEstadoEntregador(index, entregador);
            }
        }
    }
}

    