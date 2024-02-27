package com.bcopstein.demo.Aplicacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.demo.Dominio.Orcamento;
import com.bcopstein.demo.Dominio.ServicoClientes;
import com.bcopstein.demo.Dominio.ServicoItemEstoque;
import com.bcopstein.demo.Dominio.ServicoOrcamentos;

@Component
public class EfetivarOrcamento {
    @Autowired
    private ServicoItemEstoque servicoItemEstoque;

    @Autowired
    private ServicoOrcamentos servicoOrcamentos;

    @Autowired
    private ServicoClientes servicoClientes;

    public boolean run(int id){
        boolean efetivado;
        boolean itemDisponivel = servicoItemEstoque.itemDisponivel(servicoOrcamentos.itensPedidos(id));
        boolean verificarValidade = servicoOrcamentos.verificaValidade(itemDisponivel, id);
        servicoItemEstoque.baixaEstoque(servicoOrcamentos.itensPedidos(id));
        Optional<Orcamento> orcamento = servicoOrcamentos.getOrcamentoPorId(id);
        if(orcamento.isPresent()){
            Orcamento orc = orcamento.get();
            servicoClientes.salvarOrcamentoEfetivado(orc.getCli_id(), orc);
        }
        efetivado = servicoOrcamentos.efetivarOrcamento(verificarValidade, id);
        
        return efetivado;
    }
}
