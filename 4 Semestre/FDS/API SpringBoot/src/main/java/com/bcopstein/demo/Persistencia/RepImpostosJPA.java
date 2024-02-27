package com.bcopstein.demo.Persistencia;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.demo.Dominio.Imposto;
import com.bcopstein.demo.Dominio.RepImpostosInterface;

@Repository
public class RepImpostosJPA implements RepImpostosInterface {
    private List<Imposto> impostos;

    public RepImpostosJPA() {
        impostos = new LinkedList<>();
    }

    public void save(Imposto i) {
        impostos.add(i);
    }

    public List<Imposto> all() {
        return impostos;
    }

    public void clear() {
        impostos.clear();
    }

}