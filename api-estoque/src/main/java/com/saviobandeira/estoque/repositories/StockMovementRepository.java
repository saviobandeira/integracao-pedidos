package com.saviobandeira.estoque.repositories;

import com.saviobandeira.estoque.entities.StockMovement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
