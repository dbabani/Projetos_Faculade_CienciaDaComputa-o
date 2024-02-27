package com.bcopstein.demo.Dominio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.demo.Persistencia.RepDescontosJPA;

@Service
public class ServicoDescontos {
    @Autowired
    private RepDescontosJPA descontosORM;

    public List<Desconto> getDescontos() {
        return descontosORM.all();
    }

    public void clearDescontos() {
        descontosORM.clear();
    }

    public void descontoPorValor(Cliente cliente) {
        DescontoPorValor desconto = new DescontoPorValor(0);
        double ultimas_3_compras = 0;
        if (cliente.getCompras().size() >= 3) {
            ultimas_3_compras = cliente.getCompras().get(cliente.getCompras().size() - 1).getVal_final()
                    + cliente.getCompras().get(cliente.getCompras().size() - 2).getVal_final()
                    + cliente.getCompras().get(cliente.getCompras().size() - 3).getVal_final();
            if (ultimas_3_compras > 10000.00) {
                desconto.setDesconto(0.10);
                if ((ultimas_3_compras / 10000.00) >= 2.00) {
                    desconto.setDesconto(0.15);
                    if ((ultimas_3_compras / 10000.00) >= 3.00) {
                        desconto.setDesconto(0.20);
                        if ((ultimas_3_compras / 10000.00) >= 4.00) {
                            desconto.setDesconto(0.25);
                            if ((ultimas_3_compras / 10000.00) >= 5.00) {
                                desconto.setDesconto(0.30);
                            }
                        }
                    }
                }
            }
        }
        descontosORM.save(desconto);
    }

    public void descontoPorCompras(Cliente cliente) {
        DescontoPorCompras desconto = new DescontoPorCompras(0);
        Date hoje = new Date();
        int compras_ultimos_6meses = 0;
        if (cliente.getCompras().size() > 10) {
            for (Orcamento orcamento : cliente.getCompras()) {
                if ((hoje.getTime() - orcamento.getData().getTime()) < (157788 * 100000)) {
                    compras_ultimos_6meses++;
                }
                if (compras_ultimos_6meses > 10) {
                    desconto.setDesconto(0.25);
                }
            }
        }
        descontosORM.save(desconto);
    }

    public void descontoPorItens(List<ItemPedido> itens) {
        DescontoPorItens desconto = new DescontoPorItens(0);
        if (itens.size() > 5) {
            desconto.setDesconto(0.05);
        }
        descontosORM.save(desconto);
    }
}