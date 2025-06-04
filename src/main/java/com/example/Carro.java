package com.example;

public class Carro {
    private Integer idCarro;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private double precoVenda;
    private String status;

    public Carro(Integer idCarro, String placa, String marca, String modelo, Integer ano, String cor, double precoVenda, String status) {
        this.idCarro = idCarro;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.precoVenda = precoVenda;
        this.status = status;
    }

    public Integer getIdCarro() { return idCarro; }
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAno() { return ano; }
    public String getCor() { return cor; }
    public double getPrecoVenda() { return precoVenda; }
    public String getStatus() { return status; }

    public void marcarComoVendido() { this.status = "Vendido"; }
    public void enviarParaServico() { this.status = "Em Serviço"; }
}
