package com.example;

import java.util.Date;

public class Cliente extends Pessoa {
    private Integer idCliente;
    private Date dataRegistro;

    public Cliente(Integer idCliente, String cpf, String nome, String endereco, String telefone, String email, Date dataRegistro) {
        super(cpf, nome, endereco, telefone, email);
        this.idCliente = idCliente;
        this.dataRegistro = dataRegistro;
    }

    public Integer getIdCliente() { return idCliente; }
    public Date getDataRegistro() { return dataRegistro; }

    public void solicitarServico() {
        // Implementar lógica de solicitação de serviço
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " - Cliente desde: " + dataRegistro;
    }
}
