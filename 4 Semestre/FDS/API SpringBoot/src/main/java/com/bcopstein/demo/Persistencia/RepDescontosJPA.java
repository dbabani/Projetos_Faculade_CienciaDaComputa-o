package com.bcopstein.demo.Persistencia;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.demo.Dominio.Desconto;
import com.bcopstein.demo.Dominio.RepDescontosInterface;

@Repository
public class RepDescontosJPA implements RepDescontosInterface {
    private List<Desconto> descontos;

    public RepDescontosJPA() {
        descontos = new LinkedList<>();
    }

    public void save(Desconto d) {
        descontos.add(d);
    }

    public List<Desconto> all() {
        return descontos;
    }

    public void clear() {
        descontos.clear();
    }

}