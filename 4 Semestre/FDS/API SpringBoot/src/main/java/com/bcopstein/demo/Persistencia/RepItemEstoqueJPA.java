package com.bcopstein.demo.Persistencia;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.demo.Dominio.ItemEstoque;

@Repository
public class RepItemEstoqueJPA {
    private List<ItemEstoque> itensEstocados;

    public RepItemEstoqueJPA() {
        itensEstocados = new LinkedList<>();
        itensEstocados.add(new ItemEstoque(0, 10, 0, 10));
        itensEstocados.add(new ItemEstoque(1, 20, 0, 15));
        itensEstocados.add(new ItemEstoque(2, 15, 0, 0));
    }

    public void save(ItemEstoque i) {
        itensEstocados.add(i);
    }

    public List<ItemEstoque> all() {
        return itensEstocados;
    }
}