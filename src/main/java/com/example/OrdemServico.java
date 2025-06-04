package com.example;

import java.util.Date;

public class OrdemServico {
    private Integer idOrdem;
    private Date dataAgendada;
    private Date dataFinalizacao;
    private String observacoes;
    private double custoTotal;

    public OrdemServico(Integer idOrdem, Date dataAgendada, Date dataFinalizacao, String observacoes, double custoTotal) {
        this.idOrdem = idOrdem;
        this.dataAgendada = dataAgendada;
        this.dataFinalizacao = dataFinalizacao;
        this.observacoes = observacoes;
        this.custoTotal = custoTotal;
    }

    public Integer getIdOrdem() { return idOrdem; }
    public Date getDataAgendada() { return dataAgendada; }
    public Date getDataFinalizacao() { return dataFinalizacao; }
    public String getObservacoes() { return observacoes; }
    public double getCustoTotal() { return custoTotal; }

    public void finalizarServico() {
        // Lógica para finalizar serviço
    }
    public void reagendarServico(Date novaData) {
        this.dataAgendada = novaData;
    }
}
