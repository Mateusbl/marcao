package com.example;

public class Servico {
    private Integer idServico;
    private String nomeServico;
    private String descricao;
    private double custoBase;

    // Default constructor for JSON deserialization
    public Servico() {}

    public Servico(Integer idServico, String nomeServico, String descricao, double custoBase) {
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.custoBase = custoBase;
    }

    // Getters
    public Integer getIdServico() { return idServico; }
    public String getNomeServico() { return nomeServico; }
    public String getDescricao() { return descricao; }
    public double getCustoBase() { return custoBase; }

    // Setters for JSON deserialization
    public void setIdServico(Integer idServico) { this.idServico = idServico; }
    public void setNomeServico(String nomeServico) { this.nomeServico = nomeServico; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setCustoBase(double custoBase) { this.custoBase = custoBase; }

    public double calcularCustoFinal() {
        // Implementar lógica de cálculo do custo final
        return custoBase;
    }
}
