package com.example;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {
    @Column(name = "id_funcionario")
    private Integer idFuncionario;
    
    @Column(nullable = false)
    private String cargo;
    
    @Column(nullable = false)
    private double salario;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_contratacao")
    private Date dataContratacao;

    // Default constructor for JPA
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
        // Implementar lógica de registro de venda
    }
    
    public void executarServico(Servico servico) {
        // Implementar lógica de execução de serviço
    }
    
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " - Cargo: " + cargo + ", Salário: R$ " + salario;
    }
}
