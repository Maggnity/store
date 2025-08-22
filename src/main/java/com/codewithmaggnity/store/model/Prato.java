package com.codewithmaggnity.store.model;

import java.util.List;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Prato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
    
    @ElementCollection
    private List<String> itens;

    // Construtor padrão
    public Prato() {
    }

    // Construtor com parâmetros
    public Prato(Long id, String nome, double preco, List<String> itens) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.itens = itens;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
