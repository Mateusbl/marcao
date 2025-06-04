package com.example;

public class Servico {
    private Integer idServico;
    private String nomeServico;
    private String descricao;
    private double custoBase;

    public Servico(Integer idServico, String nomeServico, String descricao, double custoBase) {
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.custoBase = custoBase;
    }

    public Integer getIdServico() { return idServico; }
    public String getNomeServico() { return nomeServico; }
    public String getDescricao() { return descricao; }
    public double getCustoBase() { return custoBase; }

    public double calcularCustoFinal() {
        // Implementar lógica de cálculo do custo final
        return custoBase;
    }
}
