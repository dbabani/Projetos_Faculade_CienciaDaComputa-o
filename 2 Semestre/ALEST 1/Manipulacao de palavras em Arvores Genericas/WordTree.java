// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2023-1

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class WordTree {
    
    // Classe interna
    private class CharNode {
        private char character;
	    private String significado;
        private boolean isFinal;
        private CharNode father;
        private List<CharNode> children;

        public CharNode(char character) {
            father = null;
            this.character = character;
            children = new LinkedList<>();
        }
        
        public CharNode(char character, boolean isFinal) {
            this(character);
            this.isFinal = isFinal;
        }

        private CharNode searchNodeRef(char character, CharNode n) {
            if (n==null)
                return null;
    
            if(character == n.character) 
                return n;
    
            CharNode aux = null;
            for(int i=0; i<n.getNumberOfChildren(); i++) {
                aux = searchNodeRef(character, n.getChild(i));
                if (aux != null) 
                    return aux; 
            }
            return aux;
        }

        /**
        * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
        * @param character - caracter a ser adicionado
        * @param isfinal - se é final da palavra ou não
        */
        public CharNode addChild (char character, boolean isfinal) {
            CharNode n = new CharNode(character);

            if (children.contains(n)) {
                n = searchNodeRef(character, n);
                return n;
            }
            else if (!children.contains(n)) {
                n.father = this;
                children.add(n);
                if (isFinal) {
                    n.significado = significado;
                }
                return n;
            }
            return null;
        }

        public int getNumberOfChildren () {
            int Children_Number = children.size();
            return Children_Number;
        }
        
        public CharNode getChild (int index) {
            if ((index < 0) || (index >= children.size())) {
                throw new IndexOutOfBoundsException();
            }
            return children.get(index);
        }

    /**
    * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
    * @return a palavra
    */
    private String getWord() {
        StringBuilder wordBuilder = new StringBuilder();
        CharNode currentNode = this;

        // Percorre os nodos subindo até a raiz da árvore
        while (currentNode != null) {
            wordBuilder.insert(0, currentNode.character);
            currentNode = currentNode.father;
        }

        return wordBuilder.toString();
    }
        
        /**
        * Encontra e retorna o nodo que tem determinado caracter.
        * @param character - caracter a ser encontrado.
        */
        public CharNode findChildChar (char character) {
            if (children != null) {
                for (int index = 0; index < children.size(); index++) {
                    CharNode child = children.get(index);
                    if (child.character == character) {
                        return child;
                    }
                }
            }
            return null;
        }
        
    }

    // Atributos
    private CharNode root;
    private int totalNodes;
    private int totalWords;
    Palavra palavra = new Palavra(null, null);
    
    // Construtor
    public WordTree() {
        root = null;
        totalNodes = 0;
        totalWords = 0;
    }

    // Metodos 
    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return totalNodes;
    }
    
    /**
    *Adiciona palavra na estrutura em árvore
    *@param word
    */
    public void addWord(Palavra p) {
        if (root == null) {
            root = new CharNode('\0');
        }

        CharNode currentNode = root;
        for (int i = 0; i < p.getPalavra().length(); i++) {
            char character = p.getPalavra().charAt(i);
            CharNode child = currentNode.findChildChar(character);

            if (child == null) {
                boolean isFinal = (i == p.getPalavra().length() - 1);
                child = currentNode.addChild(character, isFinal);
                totalNodes++;
            }
            currentNode = child;
        }

        currentNode.significado = p.getSignificado();
        currentNode.isFinal = true;
        totalWords++;
    }
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        CharNode currentNode = root;
        
        // Percorre a palavra caractere por caractere
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            CharNode childNode = currentNode.findChildChar(c);
        
            // Verifica se o nó filho foi encontrado
            if (childNode != null) {
                currentNode = childNode;
            } 
        }
            return currentNode;
        }
        
    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */

    public List<String> searchAll(String prefix) {
        List<String> results = new ArrayList<>();
        CharNode prefixNode = findCharNodeForWord(prefix);

        // Verifica se o prefixo existe na árvore
        if (prefixNode != null) {
            // Chamada ao método auxiliar para percorrer os nós a partir do prefixo
            searchAllFromNode(prefixNode, results);
        }

        return results; 
    }

    private void searchAllFromNode(CharNode node, List<String> results) {
    // Verifica se o nó é final de palavra
        if (node.isFinal) {
            results.add(node.getWord());
        }

    // Percorre os filhos do nó atual
        for (CharNode child : node.children) {
            searchAllFromNode(child, results);
        }
    }

    //Auxiliar para pesquisar a palavra inteira e retornar com o significado
    public List<String> searchAllSignificado(String prefix) {
        List<String> results = new ArrayList<>();
        CharNode prefixNode = findCharNodeForWord(prefix);

        // Verifica se o prefixo existe na árvore
        if (prefixNode != null) {
            // Chamada ao método auxiliar para percorrer os nós a partir do prefixo
            searchAllFromNode2(prefixNode, results);
        }
        return results;
    }

    private void searchAllFromNode2(CharNode node, List<String> results) {
    // Verifica se o nó é final de palavra
        if (node.isFinal) {
            results.add("Palavra: " + node.getWord()  + "; " + "Significado: " + node.significado);
        }

        // Percorre os filhos do nó atual
        for (CharNode child : node.children) {
            searchAllFromNode(child, results);
        }
    }   
}   


   



