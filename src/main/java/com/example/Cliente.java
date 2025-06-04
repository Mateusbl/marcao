package com.example;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro")
    private Date dataRegistro;

    // Default constructor for JPA
    public Cliente() {
        super();
    }    public Cliente(String cpf, String nome, String endereco, String telefone, String email, Date dataRegistro) {
        super(cpf, nome, endereco, telefone, email);
        this.dataRegistro = dataRegistro;
    }

    // Getters  
    public Date getDataRegistro() { return dataRegistro; }

    // Setters for JSON deserialization
    public void setDataRegistro(Date dataRegistro) { this.dataRegistro = dataRegistro; }

    public void solicitarServico() {
        // Implementar lógica de solicitação de serviço
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " - Cliente desde: " + dataRegistro;
    }
}
