package com.bcopstein.demo.Dominio;

public class Produto {
    private int cod;
    private String desc;
    private double preco;

    public Produto(int cod, String desc, double preco) {
        this.cod = cod;
        this.desc = desc;
        this.preco = preco;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
