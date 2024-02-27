import java.time.LocalDate;

public class ListaDeRuas {

    private Node header;
    private Node trailer;
    private Node current;      
    private int count;

    private class Node {
        public Rua element;
        public ListaDeSinalizacoes lista;
        public Node next;
        public Node prev;

        public Node(Rua element, ListaDeSinalizacoes lista) {
            this.element = element;
            this.lista = lista;
            next = null;
            prev = null;
        }
    }

    public ListaDeRuas() {
        header = new Node(null, null);
        trailer = new Node(null, null);
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    ListaDeSinalizacoes listaSinal = new ListaDeSinalizacoes();



    public void reset() {
        current = header;
    }

    public Rua next() {
        if (current.next != trailer) {
            current = current.next;
            return current.element;
        }
        return null;
    }  

    public Rua prev() {
        if (current != header) {
            current = current.prev;
            return current.element;
        }
        return null;
    }
    
    


    public Rua getRuaComMaisSinalizacoes() {
        Node aux = header.next;
        Rua maisSinal = null;
        int maior = 0;
        for (int i = 0; i < count; i++) {
            if (aux.lista.size() > maior) {
                maior = aux.lista.size();
                maisSinal = aux.element;
            }
        aux = aux.next;
        }
        return maisSinal;
    }

    public boolean contains(String nomeRua) {
        Node aux = header.next;
        for(int i=0; i<count; i++) {
            if ((aux.element.getNomeDaRua()).equals(nomeRua)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    private Node containsElement(String nomeDaRua) {
        Node aux = header.next;
        while (aux != trailer) {
            if ((aux.element.getNomeDaRua()).equals(nomeDaRua)) {
                return aux;
            }
            aux = aux.next;
        }
        return null;
    } 

    public void insereOrdenado (Rua rua, Sinalizacao sinalizacao)  { 
        Node aux = containsElement(rua.getNomeDaRua());

        if (aux == null) {  
            ListaDeSinalizacoes lista = new ListaDeSinalizacoes();
            lista.add(sinalizacao);
            Node n = new Node(rua, lista);

            if (header.next == trailer) { 

                n.prev = header;
                n.next = trailer;
                trailer.prev = n;
                header.next = n;

            } 
            else if (rua.getNomeDaRua().compareTo(header.next.element.getNomeDaRua())<0) { 
                // se for menor que o primeiro, insere no inicio
                n.next = header.next;
                n.prev = header;
                header.next = n;
                n.next.prev = n;
            }
            else if (rua.getNomeDaRua().compareTo(header.next.element.getNomeDaRua())>0) {
                n.next = trailer;
                n.prev = trailer.prev;
                trailer.prev.next = n;
                trailer.prev = n;
            }
            else {
                aux = header.next;
                boolean inseriu=false;
                while (aux!=trailer && !inseriu) {
                    if (rua.getNomeDaRua().compareTo(aux.element.getNomeDaRua())<0) {
                        inseriu = true;
                        n.next = aux;
                        n.prev=aux.prev;
                        aux.prev.next = n;
                        aux.prev = n;
                    }
                    aux = aux.next;
                }
            }
            count++;
        }
        if(aux != null){
            aux.lista.add(sinalizacao);
        }


    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node aux = header.next;
        for (int i = 0; i < count; i++) {
            s.append(aux.element);
            s.append("\n");
            s.append(aux.lista);
            s.append("\n \n");
            aux = aux.next;
        }
        return s.toString();
    }   
    
    public ListaDeSinalizacoes getListaDeSinalizacoes() {
        return current.next.lista;
    }
    public String getmesmaisSinalizacoes() {
        int[] meses = new int[11];
        int mes = 0;
        Node aux = header.next;
        for(int i = 0;i<count;i++){
            for(int j= 0;j<getListaDeSinalizacoes().size();j++){
                meses[getListaDeSinalizacoes().
                getMes(j)
                ]++;

                for(int k = 0;k < meses.length; k++) {
                    if(meses[k]> mes) {
                        mes = k;
                    }
                }
            }
            for(int j = 0; j < meses.length; j++) {
                meses[j] = 0;
            }
            aux = aux.next;

        }

        switch(mes){
            case 1:
                return "Janeiro";
                
            
            case 2:
                return "Fevereiro";
                

            case 3:
                return "Marco";
                

            case 4:
                return "Abril";
                

            case 5:
                return "Maio";
                

            case 6:
                return "Junho";
                

            case 7:
                return "Julho";
                

            case 8:
                return "Agosto";
                

            case 9:
                return "Setembro";
                

            case 10:
                return "Outubro";
                

            case 11:
                return "Novembro";
                

            case 12:
                return "Dezembro";
                
                default :
                return "Opcao invalida";

                

        }
        
    
        
}

}
