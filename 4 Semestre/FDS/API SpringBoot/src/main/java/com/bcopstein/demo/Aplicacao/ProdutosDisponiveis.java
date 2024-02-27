package com.bcopstein.demo.Aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.demo.Dominio.ItemEstoque;
import com.bcopstein.demo.Dominio.Produto;
import com.bcopstein.demo.Dominio.ServicoItemEstoque;
import com.bcopstein.demo.Dominio.ServicoProdutos;

@Component
public class ProdutosDisponiveis {
    @Autowired
    private ServicoItemEstoque servicoItemEstoque;
    
    @Autowired
    private ServicoProdutos servicoProdutos;

    public List<Produto> run() {
        List<Produto> produtos;
        List<ItemEstoque> itensEstocados = servicoItemEstoque.itensDisponiveis();
        produtos = servicoProdutos.produtosDisponiveis(itensEstocados);
        return produtos;
    }
}