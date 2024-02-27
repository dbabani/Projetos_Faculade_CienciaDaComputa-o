package com.bcopstein.demo.Dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.demo.Persistencia.RepItemEstoqueJPA;

@Service
public class ServicoItemEstoque {
    @Autowired
    private RepItemEstoqueJPA itemEstoqueORM;

    public List<ItemEstoque> itensDisponiveis() {
        return itemEstoqueORM.all().stream().filter(i -> i.getQtd_atual() > 0).toList();
    }

    public boolean itemDisponivel(List<ItemPedido> itens) {
        for (ItemPedido itemPedido : itens) {
            for (ItemEstoque itemEstoque : itemEstoqueORM.all()) {
                if (itemPedido.getCodProduto() == itemEstoque.getCodProduto()) {
                    if (itemEstoque.getQtd_atual() <= 0 || itemPedido.getQuantidade() > itemEstoque.getQtd_atual()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void baixaEstoque(List<ItemPedido> itensPedidos) {
        for (ItemPedido itemPedido : itensPedidos) {
            for (ItemEstoque itemEstoque : itemEstoqueORM.all()) {
                if (itemPedido.getCodProduto() == itemEstoque.getCodProduto()) {
                    itemEstoque.setQtd_atual(itemEstoque.getQtd_atual() - itemPedido.getQuantidade());
                }
            }
        }
    }

    public int quantidadeEmEstoque(int id) {
        for (ItemEstoque item : itemEstoqueORM.all()) {
            if (item.getCodProduto() == id) {
                return item.getQtd_atual();
            }
        }
        return 0;
    }
}