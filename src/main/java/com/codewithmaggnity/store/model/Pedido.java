package com.codewithmaggnity.store.model;

import java.util.List;

public class Pedido {
    private List<Prato> itens;
    private double total;

    // Construtor padrão
    public Pedido() {
    }

    // Getters e Setters
    public List<Prato> getItens() {
        return itens;
    }

    public void setItens(List<Prato> itens) {
        this.itens = itens;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
