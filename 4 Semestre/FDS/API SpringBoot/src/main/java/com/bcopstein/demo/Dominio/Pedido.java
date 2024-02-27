package com.bcopstein.demo.Dominio;

import java.util.List;

public class Pedido {
    private int id;
    private int cli_id;
    private String cli_nome;
    private List<ItemPedido> itens;

    public Pedido(int id, int cli_id, String cli_nome, List<ItemPedido> itens) {
        this.id = id;
        this.cli_id = cli_id;
        this.cli_nome = cli_nome;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

}
