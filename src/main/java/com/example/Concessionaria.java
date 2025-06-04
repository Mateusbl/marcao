package com.example;

import java.util.ArrayList;
import java.util.List;

public class Concessionaria {
    private String idCnpj;
    private String nome;
    private String endereco;
    private String telefone;
    private List<Estoque> estoques = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();

    public Concessionaria(String idCnpj, String nome, String endereco, String telefone) {
        this.idCnpj = idCnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getIdCnpj() { return idCnpj; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public List<Estoque> getEstoques() { return estoques; }
    public List<Servico> getServicos() { return servicos; }

    public void adicionarEstoque(Estoque estoque) {
        estoques.add(estoque);
    }
    public List<Servico> listarServicosDisponiveis() {
        return servicos;
    }
}
