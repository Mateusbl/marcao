package com.example;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String cpf;
    
    @Column(nullable = false)
    private String nome;
    
    private String endereco;
    private String telefone;
    private String email;

    // Default constructor for JPA
    public Pessoa() {}

    public Pessoa(String cpf, String nome, String endereco, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters
    public Long getId() { return id; }
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    // Setters for JSON deserialization
    public void setId(Long id) { this.id = id; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }

    public void atualizarEndereco(String novoEndereco) { this.endereco = novoEndereco; }
    public void atualizarTelefone(String novoTelefone) { this.telefone = novoTelefone; }
    public String exibirResumo() { return nome + " (CPF: " + cpf + ")"; }
}
