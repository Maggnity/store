package com.codewithmaggnity.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmaggnity.store.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
