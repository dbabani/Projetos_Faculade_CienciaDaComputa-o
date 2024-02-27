package com.bcopstein.demo.Aplicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.demo.Dominio.ItemPedido;
import com.bcopstein.demo.Dominio.Orcamento;
import com.bcopstein.demo.Dominio.Produto;
import com.bcopstein.demo.Dominio.ServicoOrcamentos;
import com.bcopstein.demo.Dominio.ServicoProdutos;

@Component
public class VerificarEstatistica {
    @Autowired
    private ServicoOrcamentos servicoOrcamentos;

    @Autowired
    private ServicoProdutos servicoProdutos;

    public HashMap<String, Double> run(Date start, Date end) {
        List<Orcamento> orcamentos = servicoOrcamentos.pedidosEfetuadosPorData(start, end);
        List<ItemPedido> itensPedidos = new ArrayList<>();

        orcamentos.forEach(o -> {
            o.getPedido().getItens().forEach(p -> {
                itensPedidos.add(p);
            });
        });

        HashMap<String, Double> somaPorProduto = new HashMap<>();
        itensPedidos.forEach(item -> {
            Optional<Produto> p = servicoProdutos.getProdutoPorId(item.getCodProduto());
            if (p.isPresent()) {
                Produto prod = p.get();
                Boolean exist = somaPorProduto.containsKey(prod.getDesc());
                Double cur = exist ? somaPorProduto.get(prod.getDesc()) : 0;
                somaPorProduto.put(prod.getDesc(), cur + item.getQuantidade() * prod.getPreco());
                Double sum = somaPorProduto.containsKey("total") ? somaPorProduto.get("total") : 0;
                somaPorProduto.put("total", sum + item.getQuantidade() * prod.getPreco());
            }
        });

        Double sum = somaPorProduto.get("total");
        somaPorProduto.keySet().forEach(key -> {
            if (key != "total") {
                Double value = somaPorProduto.get(key);
                somaPorProduto.put(key, value / sum);
            }
        });

        return somaPorProduto;
    }
}