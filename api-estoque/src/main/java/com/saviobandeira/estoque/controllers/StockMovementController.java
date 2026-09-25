package com.saviobandeira.estoque.controllers;

import com.saviobandeira.estoque.services.StockMovementService;
import com.saviobandeira.estoque.dto.StockMovementDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/stock_movements")
public class StockMovementController {

    @Autowired
    private StockMovementService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<StockMovementDTO> findById(@PathVariable Long id) {
        StockMovementDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
}
