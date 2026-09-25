package com.saviobandeira.estoque.services;

import com.saviobandeira.estoque.repositories.ProductRepository;
import com.saviobandeira.estoque.entities.Product;
import com.saviobandeira.estoque.dto.ProductDTO;
import com.saviobandeira.estoque.services.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new ProductDTO(product);
    }
}
