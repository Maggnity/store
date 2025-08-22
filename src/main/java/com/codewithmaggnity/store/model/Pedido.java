package com.codewithmaggnity.store.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private Double total;

    @ManyToMany
    private List<Prato> itens = new ArrayList<>();

    // Construtor padrão
    public Pedido() {
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    };

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Prato> getItens() {
        return itens;
    }

    public void setItens(List<Prato> itens) {
        this.itens = itens;
    }
}
