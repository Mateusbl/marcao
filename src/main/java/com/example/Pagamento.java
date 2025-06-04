package com.example;

import java.util.Date;

public class Pagamento {
    private Integer idPagamento;
    private String tipo;
    private double valorTotal;
    private Date dataPagamento;
    private String status;

    public Pagamento(Integer idPagamento, String tipo, double valorTotal, Date dataPagamento, String status) {
        this.idPagamento = idPagamento;
        this.tipo = tipo;
        this.valorTotal = valorTotal;
        this.dataPagamento = dataPagamento;
        this.status = status;
    }

    public Integer getIdPagamento() { return idPagamento; }
    public String getTipo() { return tipo; }
    public double getValorTotal() { return valorTotal; }
    public Date getDataPagamento() { return dataPagamento; }
    public String getStatus() { return status; }

    public void confirmarPagamento() {
        this.status = "Confirmado";
    }
    public void cancelarPagamento() {
        this.status = "Cancelado";
    }
}
