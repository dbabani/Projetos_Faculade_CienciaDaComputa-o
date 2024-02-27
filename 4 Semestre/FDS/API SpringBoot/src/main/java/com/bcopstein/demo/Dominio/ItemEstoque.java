package com.bcopstein.demo.Dominio;

public class ItemEstoque {
    private int codProduto;
    private int qtd_max;
    private int qtd_min;
    private int qtd_atual;

    public ItemEstoque(int codProduto, int qtd_max, int qtd_min, int qtd_atual) {
        this.codProduto = codProduto;
        this.qtd_max = qtd_max;
        this.qtd_min = qtd_min;
        this.qtd_atual = qtd_atual;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getQtd_max() {
        return qtd_max;
    }

    public void setQtd_max(int qtd_max) {
        this.qtd_max = qtd_max;
    }

    public int getQtd_min() {
        return qtd_min;
    }

    public void setQtd_min(int qtd_min) {
        this.qtd_min = qtd_min;
    }

    public int getQtd_atual() {
        return qtd_atual;
    }

    public void setQtd_atual(int qtd_atual) {
        this.qtd_atual = qtd_atual;
    }
}
