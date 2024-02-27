package com.bcopstein.demo.Aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.demo.Dominio.Cliente;
import com.bcopstein.demo.Dominio.Desconto;
import com.bcopstein.demo.Dominio.Imposto;
import com.bcopstein.demo.Dominio.Orcamento;
import com.bcopstein.demo.Dominio.Pedido;
import com.bcopstein.demo.Dominio.ServicoClientes;
import com.bcopstein.demo.Dominio.ServicoDescontos;
import com.bcopstein.demo.Dominio.ServicoImpostos;
import com.bcopstein.demo.Dominio.ServicoProdutos;
import com.bcopstein.demo.Dominio.ServicoOrcamentos;

@Component
public class SolicitarOrcamento {
    @Autowired
    private ServicoOrcamentos servicoOrcamentos;

    @Autowired
    private ServicoClientes servicoCliente;

    @Autowired
    private ServicoProdutos servicoProdutos;

    @Autowired
    private ServicoDescontos servicoDescontos;

    @Autowired
    private ServicoImpostos servicoImpostos;

    public Orcamento run(Pedido pedido){
        Orcamento orcamento;
        double preco = servicoProdutos.solicitarPreco(pedido.getItens());
        servicoDescontos.descontoPorValor(servicoCliente.getCliente(pedido.getCli_id()));
        servicoDescontos.descontoPorCompras(servicoCliente.getCliente(pedido.getCli_id()));
        servicoDescontos.descontoPorItens(pedido.getItens());
        List<Desconto> descontos = servicoDescontos.getDescontos();
        Imposto imposto = servicoImpostos.criaImposto(0.1);
        orcamento = servicoOrcamentos.solicitarOrcamento(pedido, preco, descontos, imposto);
        servicoDescontos.clearDescontos();
        return orcamento;
    }

    public List<Cliente> listar(){
        return servicoCliente.clientes();
    }
}
