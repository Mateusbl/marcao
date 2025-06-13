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

    /**
     * Método artificialmente complexo para fins acadêmicos.
     * Complexidade ciclomática > 10.
     */
    public String avaliarPerfil(int idade, boolean empregado, boolean possuiCarro, boolean possuiCasa, double renda, int dependentes, boolean possuiPlanoSaude, boolean possuiDividas, boolean fazFaculdade, boolean praticaEsporte) {
        StringBuilder perfil = new StringBuilder();

        if (idade < 18) {
            perfil.append("Menor de idade. ");
        } else if (idade < 30) {
            perfil.append("Jovem adulto. ");
        } else if (idade < 60) {
            perfil.append("Adulto. ");
        } else {
            perfil.append("Idoso. ");
        }

        if (empregado) {
            perfil.append("Empregado. ");
            if (renda > 5000) {
                perfil.append("Alta renda. ");
            } else if (renda > 2000) {
                perfil.append("Renda média. ");
            } else {
                perfil.append("Baixa renda. ");
            }
        } else {
            perfil.append("Desempregado. ");
        }

        if (possuiCarro && possuiCasa) {
            perfil.append("Possui carro e casa. ");
        } else if (possuiCarro) {
            perfil.append("Possui apenas carro. ");
        } else if (possuiCasa) {
            perfil.append("Possui apenas casa. ");
        } else {
            perfil.append("Não possui carro nem casa. ");
        }

        if (dependentes > 0) {
            perfil.append("Tem dependentes. ");
        }

        if (possuiPlanoSaude) {
            perfil.append("Possui plano de saúde. ");
        }

        if (possuiDividas) {
            perfil.append("Possui dívidas. ");
        }

        if (fazFaculdade) {
            perfil.append("Faz faculdade. ");
        }

        if (praticaEsporte) {
            perfil.append("Pratica esportes. ");
        }

        return perfil.toString();
    }
}
