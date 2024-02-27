import java.time.LocalDate;

public class ListaDeSinalizacoes {

    private class Node {
        public Sinalizacao element;
        public Node next;

        public Node(Sinalizacao element) {
            this.element = element;
            next = null;
        }

        public Node(Sinalizacao element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private Node current;
    private int count;

    public ListaDeSinalizacoes() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add (Sinalizacao element) {
        Node aux = new Node(element);
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }

    public int size() {
        return count;
    }

    public int getMes(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        
        if (index==count-1)
            return tail.element.getImplantacao().getMonthValue();

        Node aux = head;
        for(int i=0; i<index; i++) {
            aux = aux.next; 
        }

        return aux.element.getImplantacao().getMonthValue();
    }

    public LocalDate getDataImplantacao(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        
        if (index==count-1)
            return tail.element.getImplantacao();

        Node aux = head;
        for(int i=0; i<index; i++) {
            aux = aux.next; 
        }
    
        return aux.element.getImplantacao();
    }

    public String getMenorData() {
        Node aux = head;
        LocalDate menor = aux.element.getImplantacao();
        Sinalizacao sinal = aux.element;

        for (int i = 0; i < count; i++) {
            if (aux.element.getImplantacao().isBefore(menor)) {
                menor = aux.element.getImplantacao();
                sinal = aux.element;
            }
            aux = aux.next;
        }

        int ano, mes, dia;
        ano = menor.getYear();
        mes = menor.getMonthValue();
        dia = menor.getDayOfMonth();

        return "Data da primeira implantacao (mais antiga): " + dia + "/" + mes + "/" + ano + " --> Referente a" + sinal;
    }

    public String getMaiorData() {
        Node aux = head;
        LocalDate maior = aux.element.getImplantacao();
        Sinalizacao sinal = aux.element;

        for (int i = 0; i < count; i++) {
            if (!aux.element.getImplantacao().isBefore(maior)) {
                maior = aux.element.getImplantacao();
                sinal = aux.element;
            }
            aux = aux.next;
        }

        int ano, mes, dia;
        ano = maior.getYear();
        mes = maior.getMonthValue();
        dia = maior.getDayOfMonth();

        return "Data da ultima implantacao (mais nova): " + dia + "/" + mes + "/" + ano + " --> Referente a" + sinal;
    }

    public void reset() {
        current = head;
    }    

    public Sinalizacao next() {
        Sinalizacao numPosCurrent = null;
        if (current != null) {
            numPosCurrent = current.element; 
            current = current.next; 
            return numPosCurrent; 
        }
        return null;
    }

    public Sinalizacao getSinalizacao() {
        return current.element;
    }

    @Override
    public String toString() {
        Node aux = head;
        String sinalizacoes = aux.element.toString();
        while (aux.next != null) {
            aux = aux.next;
            sinalizacoes = sinalizacoes + "\n" + aux.element.toString();
        }
        return sinalizacoes;
    }

}



