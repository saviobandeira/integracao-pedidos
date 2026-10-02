package com.saviobandeira.estoque.services;

import com.saviobandeira.estoque.repositories.MovementRepository;
import com.saviobandeira.estoque.entities.Movement;
import com.saviobandeira.estoque.dto.MovementDTO;
import com.saviobandeira.estoque.services.exceptions.ResourceNotFoundException;
import com.saviobandeira.estoque.dto.MovementRequestDTO;
import com.saviobandeira.estoque.entities.enums.MovementType;
import com.saviobandeira.estoque.repositories.ProductRepository;
import com.saviobandeira.estoque.entities.Product;
import com.saviobandeira.estoque.services.exceptions.InsufficientBalanceException;
import com.saviobandeira.estoque.services.exceptions.ReversalNotAllowedException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovementService {

    @Autowired
    private MovementRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public MovementDTO findById(Long id) {
        Movement stockMovement = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new MovementDTO(stockMovement);
    }

    @Transactional
    public MovementDTO insert(Long productId, MovementRequestDTO request, MovementType type) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum produto encontrado com o id" + productId)
        );

        Integer balance = product.getBalance();
        Integer quantity = type == MovementType.OUT?
                -request.getQuantity():
                request.getQuantity();
        if (type == MovementType.OUT && balance < -(quantity)) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
        product.setBalance(balance + quantity);

        Movement movement = new Movement();

        movement.setQuantity(quantity);
        movement.setOrderNumber(request.getOrderNumber());
        movement.setProduct(product);
        movement.setType(type);

        movement = repository.save(movement);
        return new MovementDTO(movement);
    }

    @Transactional
    public MovementDTO reverse(Long id) {
        Movement movement = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi encontrada nenhuma movimentação com o id " + id)
        );

        MovementType type = movement.getType();
        if(type == MovementType.REVERSAL) {
            throw new ReversalNotAllowedException("Não é possivel reverter uma movimentação de reverção");
        }

        Product product = movement.getProduct();

        Integer quantity = movement.getQuantity();
        Integer balance = product.getBalance();
        if (type == MovementType.IN && balance < quantity) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }

        Integer negatedQuantity = -(quantity);
        product.setBalance(balance + negatedQuantity);

        movement.setOrderNumber(movement.getOrderNumber());
        movement.setType(MovementType.REVERSAL);
        movement.setQuantity(0);
        movement = repository.save(movement);
        return new MovementDTO(movement);
    }
}