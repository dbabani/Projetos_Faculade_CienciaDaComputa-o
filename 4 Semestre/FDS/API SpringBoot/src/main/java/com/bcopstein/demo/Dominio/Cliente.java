package com.bcopstein.demo.Dominio;

import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private List<Orcamento> compras;

    public Cliente(int id, String nome, List<Orcamento> compras) {
        this.id = id;
        this.nome = nome;
        this.compras = compras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Orcamento> getCompras() {
        return compras;
    }

    public void setCompras(List<Orcamento> compras) {
        this.compras = compras;
    }

    public void addCompra(Orcamento o){
        this.compras.add(o);
    }

}
