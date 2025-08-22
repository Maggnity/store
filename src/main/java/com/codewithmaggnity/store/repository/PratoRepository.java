package com.codewithmaggnity.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithmaggnity.store.model.Prato;

public interface PratoRepository extends JpaRepository<Prato, Long> {
}
