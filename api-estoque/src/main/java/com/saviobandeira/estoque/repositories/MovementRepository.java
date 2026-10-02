package com.saviobandeira.estoque.repositories;

import com.saviobandeira.estoque.entities.Movement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {
}
