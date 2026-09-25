package com.saviobandeira.estoque.services;

import com.saviobandeira.estoque.repositories.StockMovementRepository;
import com.saviobandeira.estoque.entities.StockMovement;
import com.saviobandeira.estoque.dto.StockMovementDTO;
import com.saviobandeira.estoque.services.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository repository;

    @Transactional(readOnly = true)
    public StockMovementDTO findById(Long id) {
        StockMovement stockMovement = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new StockMovementDTO(stockMovement);
    }
}
