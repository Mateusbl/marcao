package com.example;

import java.util.Date;

public class Funcionario extends Pessoa {
    private Integer idFuncionario;
    private String cargo;
    private double salario;
    private Date dataContratacao;

    // Default constructor for JSON deserialization
    public Funcionario() {
        super();
    }

    public Funcionario(Integer idFuncionario, String cpf, String nome, String endereco, String telefone, String email, String cargo, double salario, Date dataContratacao) {
        super(cpf, nome, endereco, telefone, email);
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }

    // Getters
    public Integer getIdFuncionario() { return idFuncionario; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }
    public Date getDataContratacao() { return dataContratacao; }

    // Setters for JSON deserialization
    public void setIdFuncionario(Integer idFuncionario) { this.idFuncionario = idFuncionario; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public void setSalario(double salario) { this.salario = salario; }
    public void setDataContratacao(Date dataContratacao) { this.dataContratacao = dataContratacao; }

    public void registrarVenda(Venda venda) {
        // Exemplo: adicionar venda a uma lista de vendas do funcionário
        // vendas.add(venda);
    }
    public void executarServico(Servico servico) {
        // Exemplo: adicionar serviço a uma lista de serviços executados
        // servicosExecutados.add(servico);
    }
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " - Cargo: " + cargo + ", Salário: R$ " + salario;
    }
}
