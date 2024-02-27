package com.bcopstein.demo.Dominio;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.demo.Persistencia.RepProdutosJPA;

@Service
public class ServicoProdutos {
    @Autowired
    private RepProdutosJPA produtosORM;

    public Optional<Produto> getProdutoPorId(int id) {
        return produtosORM.all().stream().filter(p -> p.getCod() == id).findFirst();
    }

    public List<Produto> produtosDisponiveis(List<ItemEstoque> itensDisponiveis) {
        List<Produto> produtosDisponiveis = new LinkedList<>();
        for (Produto produto : produtosORM.all()) {
            for (ItemEstoque item : itensDisponiveis) {
                if (produto.getCod() == item.getCodProduto()) {
                    produtosDisponiveis.add(produto);
                }
            }
        }
        return produtosDisponiveis;
    }

    public double solicitarPreco(List<ItemPedido> itens) {
        double preco = 0.0;
        for (Produto produto : produtosORM.all()) {
            for (ItemPedido item : itens) {
                if (produto.getCod() == item.getCodProduto()) {
                    preco += produto.getPreco() * item.getQuantidade();
                }
            }
        }
        return preco;
    }
}