package com.saviobandeira.estoque.services;

import com.saviobandeira.estoque.repositories.StockMovementRepository;
import com.saviobandeira.estoque.entities.StockMovement;
import com.saviobandeira.estoque.dto.StockMovementDTO;
import com.saviobandeira.estoque.services.exceptions.ResourceNotFoundException;
import com.saviobandeira.estoque.dto.StockMovementRequestDTO;
import com.saviobandeira.estoque.entities.enums.StockMovementType;
import com.saviobandeira.estoque.repositories.ProductRepository;
import com.saviobandeira.estoque.entities.Product;
import com.saviobandeira.estoque.services.exceptions.InsufficientBalanceException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public StockMovementDTO findById(Long id) {
        StockMovement stockMovement = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new StockMovementDTO(stockMovement);
    }

    @Transactional
    public StockMovementDTO insert(Long productId, StockMovementRequestDTO request, StockMovementType type) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum produto encontrado com o id " + productId)
        );

        Integer balance = product.getBalance();
        Integer quantity = type == StockMovementType.OUT?
                -request.getQuantity():
                request.getQuantity();
        if (type == StockMovementType.OUT && balance < -(quantity)) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
        product.setBalance(balance + quantity);

        StockMovement stockMovement = new StockMovement();

        stockMovement.setQuantity(quantity);
        stockMovement.setOrderNumber(request.getOrderNumber());
        stockMovement.setProduct(product);
        stockMovement.setType(type);

        stockMovement = repository.save(stockMovement);
        return new StockMovementDTO(stockMovement);
    }
}