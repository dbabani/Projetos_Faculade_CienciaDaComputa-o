package com.bcopstein.demo.Interface;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.demo.Aplicacao.EfetivarOrcamento;
import com.bcopstein.demo.Aplicacao.ProdutosDisponiveis;
import com.bcopstein.demo.Aplicacao.QuantidadeEmEstoque;
import com.bcopstein.demo.Aplicacao.SolicitarOrcamento;
import com.bcopstein.demo.Aplicacao.Validade21;
import com.bcopstein.demo.Aplicacao.VerificarEstatistica;
import com.bcopstein.demo.Dominio.Orcamento;
import com.bcopstein.demo.Dominio.Pedido;
import com.bcopstein.demo.Dominio.Produto;
import com.bcopstein.demo.Dominio.ToCalendar;

@RestController
public class Controller {
    @Autowired
    private ProdutosDisponiveis produtosDisponiveis;
    @Autowired
    private SolicitarOrcamento solicitarOrcamento;
    @Autowired
    private EfetivarOrcamento efetivarOrcamento;
    @Autowired
    private VerificarEstatistica estatistica;
    @Autowired
    private QuantidadeEmEstoque quantidadeEmEstoque;
    @Autowired
    private Validade21 validade21;

    @GetMapping("")
    @CrossOrigin("*")
    public String loja() {
        return "Bem-vindo à loja!";
    }

    @GetMapping("produtos")
    @CrossOrigin("*")
    public List<Produto> produtosDisponiveis() {
        return produtosDisponiveis.run();
    }

    @PostMapping("orcamento")
    @CrossOrigin("*")
    public Orcamento solicitarOrcamento(@RequestBody Pedido pedido) {
        return solicitarOrcamento.run(pedido);
    }

    @PostMapping("efetivar")
    @CrossOrigin("*")
    public boolean efetivarOrcamento(@RequestParam int id) {
        return efetivarOrcamento.run(id);
    }

    // Estatistica 1
    @GetMapping("estatistica")
    @CrossOrigin("*")
    public HashMap<String, Double> verificarEstatistica(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        String[] startSplit = start.split("-");
        String[] endSplit = end.split("-");

        Date startDate = ToCalendar.getCalendar(Integer.parseInt(startSplit[2]), Integer.parseInt(startSplit[1]) - 1,
                Integer.parseInt(startSplit[0])).getTime();
        Date endDate = ToCalendar.getCalendar(Integer.parseInt(endSplit[2]), Integer.parseInt(endSplit[1]) - 1,
                Integer.parseInt(endSplit[0])).getTime();

        HashMap<String, Double> soma = estatistica.run(startDate, endDate);

        return soma;
    }

    // Estatistica 2
    @GetMapping("estatistica2")
    @CrossOrigin("*")
    public int quantidadeEmEstoque(@RequestParam int id, @RequestParam int id2, @RequestParam int id3) {
        return quantidadeEmEstoque.run(id, id2, id3);
    }

    // Estatistica 3
    @GetMapping("estatistica3")
    @CrossOrigin("*")
    public List<Orcamento> validade21() {
        return validade21.run();
    }
}