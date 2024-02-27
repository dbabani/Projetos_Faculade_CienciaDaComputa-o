package com.bcopstein.demo.Persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.demo.Dominio.Cliente;
import com.bcopstein.demo.Dominio.Orcamento;
import com.bcopstein.demo.Dominio.RepClientesInterface;

@Repository
public class RepClientesJPA implements RepClientesInterface {
    private List<Cliente> clientes;

    public RepClientesJPA() {
        clientes = new LinkedList<>();
        List<Orcamento> orcamentos = new ArrayList<>();

        clientes.add(new Cliente(0, "Leo", orcamentos));
    }

    public void save(Cliente c) {
        clientes.add(c);
    }

    public List<Cliente> all() {
        return clientes;
    }

}