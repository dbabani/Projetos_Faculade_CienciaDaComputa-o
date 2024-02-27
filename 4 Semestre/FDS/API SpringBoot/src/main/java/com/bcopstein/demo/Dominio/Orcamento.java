package com.bcopstein.demo.Dominio;

import java.util.Date;

public class Orcamento {
    private int id;
    private Date data;
    private int cli_id;
    private String cli_nome;
    private Pedido pedido;
    private double sum_itens;
    private Imposto imposto;
    private Desconto desconto;
    private double val_final;
    private boolean efetivado;
    private Date validade;
    private Date compra;

    public Orcamento(int id, Date data, int cli_id, String cli_nome, Pedido pedido, double sum_itens, Imposto imposto,
            Desconto desconto, double val_final, boolean efetivado, Date validade, Date compra) {
        this.id = id;
        this.data = data;
        this.cli_id = cli_id;
        this.cli_nome = cli_nome;
        this.pedido = pedido;
        this.sum_itens = sum_itens;
        this.imposto = imposto;
        this.desconto = desconto;
        this.val_final = val_final;
        this.efetivado = efetivado;
        this.validade = validade;
        this.compra = compra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public double getSum_itens() {
        return sum_itens;
    }

    public void setSum_itens(double sum_itens) {
        this.sum_itens = sum_itens;
    }

    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public Desconto getDesconto() {
        return desconto;
    }

    public void setDesconto(Desconto desconto) {
        this.desconto = desconto;
    }

    public double getVal_final() {
        return val_final;
    }

    public void setVal_final(double val_final) {
        this.val_final = val_final;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void setEfetivado(boolean efetivado) {
        this.efetivado = efetivado;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Date getCompra() {
        return compra;
    }

    public void setCompra(Date compra) {
        this.compra = compra;
    }

}
