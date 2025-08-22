package com.codewithmaggnity.store.model;

import java.util.List;

public class Prato {
    private int id;
    private String nome;
    private double preco;
    private List<String> itens;

    // Construtor padrão
    public Prato() {
    }

    // Construtor com parâmetros
    public Prato(int id, String nome, double preco, List<String> itens) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.itens = itens;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<String> getItens() {
        return itens;
    }

    public void setItens(List<String> itens) {
        this.itens = itens;
    }
}
