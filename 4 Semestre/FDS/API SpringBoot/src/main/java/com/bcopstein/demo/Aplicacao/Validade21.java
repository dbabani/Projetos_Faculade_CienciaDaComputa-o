package com.bcopstein.demo.Aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.demo.Dominio.Orcamento;
import com.bcopstein.demo.Dominio.ServicoOrcamentos;

@Component
public class Validade21 {
    @Autowired
    private ServicoOrcamentos servicoOrcamentos;

    public List<Orcamento> run() {
        List<Orcamento> orcamentos21;
        orcamentos21 = servicoOrcamentos.getOrcamentos21dias();
        return orcamentos21;
    }

}
