package com.bcopstein.demo.Aplicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.demo.Dominio.ServicoItemEstoque;

@Component
public class QuantidadeEmEstoque {
    @Autowired
    private ServicoItemEstoque servicoItemEstoque;

    public int run(int id, int id2, int id3) {
        int quantidadeEmEstoque = 0;
        quantidadeEmEstoque += servicoItemEstoque.quantidadeEmEstoque(id);
        quantidadeEmEstoque += servicoItemEstoque.quantidadeEmEstoque(id2);
        quantidadeEmEstoque += servicoItemEstoque.quantidadeEmEstoque(id3);
        return quantidadeEmEstoque;
    }
}
