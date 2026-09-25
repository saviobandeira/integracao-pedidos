package com.saviobandeira.estoque.repositories;

import com.saviobandeira.estoque.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
