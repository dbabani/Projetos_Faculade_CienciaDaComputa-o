package com.bcopstein.demo.Dominio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.demo.Persistencia.RepClientesJPA;

@Service
public class ServicoClientes {
    @Autowired
    private RepClientesJPA clientesORM;

    public Cliente getCliente(int cli_id) {
        for (Cliente cliente : clientesORM.all()) {
            if (cliente.getId() == cli_id) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> clientes() {
        return clientesORM.all();
    }

    public void salvarOrcamentoEfetivado(int id, Orcamento o) {
        Optional<Cliente> cliente = clientesORM.all().stream().filter(c -> c.getId() == id).findFirst();
        if (cliente.isPresent()) {
            Cliente cli = cliente.get();
            cli.addCompra(o);
        }
    }
}