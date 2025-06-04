package com.example;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private Integer idEstoque;
    private String localizacao;
    private Integer capacidadeMaxima;
    private List<Carro> carros = new ArrayList<>();

    public Estoque(Integer idEstoque, String localizacao, Integer capacidadeMaxima) {
        this.idEstoque = idEstoque;
        this.localizacao = localizacao;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Integer getIdEstoque() { return idEstoque; }
    public String getLocalizacao() { return localizacao; }
    public Integer getCapacidadeMaxima() { return capacidadeMaxima; }
    public List<Carro> getCarros() { return carros; }

    public void adicionarCarro(Carro carro) {
        if (carros.size() < capacidadeMaxima) {
            carros.add(carro);
        }
    }
    public void removerCarro(Carro carro) {
        carros.remove(carro);
    }
    public List<Carro> listarCarrosDisponiveis() {
        return carros;
    }
}
