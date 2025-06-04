package com.example;

import java.util.Date;
import java.util.List;

public class Venda {
    private Integer idVenda;
    private Date dataVenda;
    private String enderecoEntrega;
    private List<ItemVenda> itens;
    private Pagamento pagamento;

    public Venda(Integer idVenda, Date dataVenda, String enderecoEntrega, List<ItemVenda> itens, Pagamento pagamento) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = itens;
        this.pagamento = pagamento;
    }

    public Integer getIdVenda() { return idVenda; }
    public Date getDataVenda() { return dataVenda; }
    public String getEnderecoEntrega() { return enderecoEntrega; }
    public List<ItemVenda> getItens() { return itens; }
    public Pagamento getPagamento() { return pagamento; }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(ItemVenda::calcularSubtotal).sum();
    }

    public void confirmarVenda() {
        // Lógica para confirmar venda
    }
}
