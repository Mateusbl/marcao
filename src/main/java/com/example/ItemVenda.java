package com.example;

public class ItemVenda {
    private int quantidade;
    private double precoUnitario;
    private Carro carro;

    public ItemVenda(int quantidade, double precoUnitario, Carro carro) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.carro = carro;
    }

    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
    public Carro getCarro() { return carro; }

    public double calcularSubtotal() {
        return quantidade * precoUnitario;
    }
}
