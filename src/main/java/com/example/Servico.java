package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Long idServico;
    
    @Column(name = "nome_servico", nullable = false)
    private String nomeServico;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "custo_base", nullable = false)
    private double custoBase;

    // Default constructor for JPA
    public Servico() {}    public Servico(String nomeServico, String descricao, double custoBase) {
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.custoBase = custoBase;
    }

    // Getters
    public Long getIdServico() { return idServico; }
    public String getNomeServico() { return nomeServico; }
    public String getDescricao() { return descricao; }
    public double getCustoBase() { return custoBase; }

    // Setters for JSON deserialization
    public void setIdServico(Long idServico) { this.idServico = idServico; }
    public void setNomeServico(String nomeServico) { this.nomeServico = nomeServico; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setCustoBase(double custoBase) { this.custoBase = custoBase; }

    public double calcularCustoFinal() {
        // Implementar lógica de cálculo do custo final
        return custoBase;
    }
}
