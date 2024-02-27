package com.bcopstein.demo.Dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.demo.Persistencia.RepOrcamentosJPA;

@Service
public class ServicoOrcamentos {
    @Autowired
    private RepOrcamentosJPA orcamentosRep;

    public Orcamento solicitarOrcamento(Pedido pedido, double preco, List<Desconto> descontos, Imposto imposto) {
        Date hoje = new Date();
        Date validade = new Date();
        Calendar calendario = Calendar.getInstance();
        int mes = calendario.get(Calendar.MONTH);
        if (mes == 0 || mes == 1) {
            // validade.setTime(hoje.getTime() + 3024 * 1000000);
            validade.setTime(hoje.getTime() + 35 * 24 * 60 * 60 * 1000);
        } else {
            // validade.setTime(hoje.getTime() + 18144 * 100000);
            validade.setTime(hoje.getTime() + 21 * 24 * 60 * 60 * 1000);
        }
        Desconto desconto = new Desconto(0);
        for (Desconto desconto2 : descontos) {
            for (Desconto desconto3 : descontos) {
                if (desconto2.getDesconto() > desconto3.getDesconto()) {
                    desconto = desconto2;
                }
            }
        }
        double val_final = preco + (preco * imposto.getImposto()) - (preco * desconto.getDesconto());
        Orcamento o = new Orcamento(orcamentosRep.all().size(), hoje, pedido.getCli_id(), pedido.getCli_nome(), pedido,
                preco, imposto, desconto, val_final, false, validade, null);
        orcamentosRep.save(o);
        return o;
    }

    public List<ItemPedido> itensPedidos(int id) {
        for (Orcamento orcamento : orcamentosRep.all()) {
            if (orcamento.getId() == id) {
                return orcamento.getPedido().getItens();
            }
        }
        return null;
    }

    public boolean verificaValidade(boolean itemDisponivel, int id) {
        Date hoje = new Date();
        if (itemDisponivel == false) {
            return false;
        } else {
            for (Orcamento orcamento : orcamentosRep.all()) {
                if (orcamento.getId() == id) {
                    if (hoje.getTime() > orcamento.getValidade().getTime()) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean efetivarOrcamento(boolean verificaValidade, int id) {
        Date hoje = new Date();
        if (verificaValidade == false) {
            return false;
        } else {
            for (Orcamento orcamento : orcamentosRep.all()) {
                if (orcamento.getId() == id) {
                    orcamento.setEfetivado(true);
                    orcamento.setCompra(hoje);
                }
            }
            return true;
        }
    }

    public List<Orcamento> pedidosEfetuadosPorData(Date start, Date end) {
        List<Orcamento> orcamentos = orcamentosRep.all();
        return orcamentos.stream().filter(
                orc -> orc.isEfetivado() && orc.getCompra().getTime() >= start.getTime()
                        && orc.getCompra().getTime() <= end.getTime())
                .toList();
    }

    public Optional<Orcamento> getOrcamentoPorId(int id) {
        return orcamentosRep.all().stream().filter(o -> o.getId() == id).findFirst();
    }

    public List<Orcamento> getOrcamentos21dias() {
        return orcamentosRep.all().stream()
                .filter(o -> o.getValidade().getTime() - o.getData().getTime() == 21 * 24 * 60 * 60 * 1000).toList();
    }
}
