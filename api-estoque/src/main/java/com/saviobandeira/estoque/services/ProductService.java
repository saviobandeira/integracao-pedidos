package com.saviobandeira.estoque.services;

import com.saviobandeira.estoque.repositories.ProductRepository;
import com.saviobandeira.estoque.entities.Product;
import com.saviobandeira.estoque.dto.ProductDTO;
import com.saviobandeira.estoque.services.exceptions.ResourceNotFoundException;
import com.saviobandeira.estoque.dto.ProductRequestDTO;
import com.saviobandeira.estoque.services.exceptions.DuplicateResourceException;

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

    @Transactional
    public ProductDTO insert(ProductRequestDTO request) {
        String code = request.getCode();
        boolean exists = repository.existsByCode(code);
        if (exists) {
            throw new DuplicateResourceException("Cógido duplicado");
        }
        Product product = new Product();

        product.setCode(code);
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        product = repository.save(product);
        return new ProductDTO(product);
    }
}
