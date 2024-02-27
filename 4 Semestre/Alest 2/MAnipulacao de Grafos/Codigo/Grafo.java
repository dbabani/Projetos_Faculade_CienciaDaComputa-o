import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {
  protected static final String NEWLINE = System.getProperty("line.separator");

  protected Map<String, List<Edge>> graph;

  public Grafo() {
    graph = new HashMap<>();
  }

  public Grafo(String filename) { // ALTERAR PARA INCLURI DE FORMA CORRETA
    this();
    In in = new In(filename);
    String line;

    while ((line = in.readLine()) != null) {
      int controlador = 0;
      String[] edge = line.split(" ");
      String verticeOrigem = edge[edge.length - 1]; // o mesmo na linha inteira
      String verticeDestino;
      int peso;

      while (!(edge[controlador].equals("->"))) {
        verticeDestino = edge[controlador + 1];
        peso = Integer.parseInt(edge[controlador]);
        addEdge(verticeDestino, verticeOrigem, peso);
        controlador = controlador + 2;
      }
    }
    in.close();
  }

  public void addEdge(String v, String w, int weight) {
    Edge e = new Edge(v, w, weight);
    addToList(v, e);
  }

  public Iterable<Edge> getAdj(String v) {
    return graph.get(v);
  }

  public Set<String> getVerts() {
    return graph.keySet();
  }

  public String toDot() {
    Set<String> edges = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    sb.append("graph {" + NEWLINE);
    sb.append("rankdir = LR;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (String v : getVerts().stream().sorted().toList()) {
      for (Edge e : getAdj(v)) {
        String edge = e.toString();
        if (!edges.contains(edge)) {
          sb.append(String.format("%s -- %s [peso=\"%d\"]", e.getV(), e.getW(), e.getWeight()) + NEWLINE);
          edges.add(edge);
        }
      }
    }
    sb.append("}" + NEWLINE);
    return sb.toString();
  }

  protected List<Edge> addToList(String v, Edge e) {
    List<Edge> list = graph.get(v);
    if (list == null)
      list = new LinkedList<>();
    list.add(e);
    graph.put(v, list);
    return list;
  }

  public BigInteger calcularHidrogenio(String v) {
    if (v.equals("ouro")) {
      BigInteger valor = BigInteger.ONE;
      return valor;
    }
    BigInteger resultado = BigInteger.valueOf(0);
    List<Edge> listaFilhos = graph.get(v);

    for (Edge edge : listaFilhos) {
      BigInteger valorAresta = BigInteger.valueOf(edge.getWeight());
      BigInteger valorFilho = calcularHidrogenio(edge.getW());

      resultado = resultado.add(valorAresta.multiply(valorFilho));
    }

    return resultado;
  }

}
