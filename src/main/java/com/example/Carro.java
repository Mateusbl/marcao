package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carro")
    private Long id;
    
    private Integer idCarro;
    
    @Column(unique = true)
    private String placa;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
    private String modelo;
    
    @Column(nullable = false)
    private Integer ano;
    
    private String cor;
    
    @Column(name = "preco_venda", nullable = false)
    private double precoVenda;
    
    @Column(nullable = false)
    private String status;

    // Default constructor for JPA
    public Carro() {}

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

    // Getters
    public Long getId() { return id; }
    public Integer getIdCarro() { return idCarro; }
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAno() { return ano; }
    public String getCor() { return cor; }
    public double getPrecoVenda() { return precoVenda; }
    public String getStatus() { return status; }

    // Setters for JSON deserialization
    public void setId(Long id) { this.id = id; }
    public void setIdCarro(Integer idCarro) { this.idCarro = idCarro; }
    public void setPlaca(String placa) { this.placa = placa; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAno(Integer ano) { this.ano = ano; }
    public void setCor(String cor) { this.cor = cor; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }
    public void setStatus(String status) { this.status = status; }

    public void marcarComoVendido() { this.status = "Vendido"; }
    public void enviarParaServico() { this.status = "Em Serviço"; }
}
