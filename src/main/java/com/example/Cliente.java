package com.example;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
    @Column(name = "id_cliente")
    private Integer idCliente;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro")
    private Date dataRegistro;

    // Default constructor for JPA
    public Cliente() {
        super();
    }

    public Cliente(Integer idCliente, String cpf, String nome, String endereco, String telefone, String email, Date dataRegistro) {
        super(cpf, nome, endereco, telefone, email);
        this.idCliente = idCliente;
        this.dataRegistro = dataRegistro;
    }

    // Getters
    public Integer getIdCliente() { return idCliente; }
    public Date getDataRegistro() { return dataRegistro; }

    // Setters for JSON deserialization
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public void setDataRegistro(Date dataRegistro) { this.dataRegistro = dataRegistro; }

    public void solicitarServico() {
        // Implementar lógica de solicitação de serviço
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " - Cliente desde: " + dataRegistro;
    }
}
