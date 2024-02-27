package com.bcopstein.demo.Dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.demo.Persistencia.RepImpostosJPA;

@Service
public class ServicoImpostos {
    @Autowired
    private RepImpostosJPA impostosORM;

    public List<Imposto> getImpostos() {
        return impostosORM.all();
    }

    public void clearImpostos() {
        impostosORM.clear();
    }

    public Imposto criaImposto(double imposto) {
        Imposto i = new Imposto(imposto);
        impostosORM.save(i);
        return i;
    }
}