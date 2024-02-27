package com.bcopstein.demo.Dominio;

public class ItemPedido {
    private int codProduto;
    private int quantidade;

    public ItemPedido(int codProduto, int quantidade) {
        this.codProduto = codProduto;
        this.quantidade = quantidade;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
