package com.example;

import java.util.Date;

public class Funcionario extends Pessoa {
    private Integer idFuncionario;
    private String cargo;
    private double salario;
    private Date dataContratacao;

    public Funcionario(Integer idFuncionario, String cpf, String nome, String endereco, String telefone, String email, String cargo, double salario, Date dataContratacao) {
        super(cpf, nome, endereco, telefone, email);
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }

    public Integer getIdFuncionario() { return idFuncionario; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }
    public Date getDataContratacao() { return dataContratacao; }

    public void registrarVenda(Venda venda) {
        // Implementar lógica de registro de venda
    }
    public void executarServico(Servico servico) {
        // Implementar lógica de execução de serviço
    }
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " - Cargo: " + cargo;
    }
}
