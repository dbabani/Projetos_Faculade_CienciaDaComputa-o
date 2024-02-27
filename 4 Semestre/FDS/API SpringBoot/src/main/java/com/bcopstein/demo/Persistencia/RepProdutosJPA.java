package com.bcopstein.demo.Persistencia;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.demo.Dominio.Produto;

@Repository
public class RepProdutosJPA {
    private List<Produto> produtos;

    public RepProdutosJPA() {
        produtos = new LinkedList<>();
        produtos.add(new Produto(0, "banana", 100.00));
        produtos.add(new Produto(1, "maçã", 150.00));
        produtos.add(new Produto(2, "pera", 200.00));
    }

    public void save(Produto p) {
        produtos.add(p);
    }

    public List<Produto> all() {
        return produtos;
    }
}