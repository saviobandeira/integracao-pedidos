package com.saviobandeira.estoque.controllers;

import com.saviobandeira.estoque.services.StockMovementService;
import com.saviobandeira.estoque.dto.StockMovementDTO;
import com.saviobandeira.estoque.dto.StockMovementRequestDTO;
import com.saviobandeira.estoque.entities.enums.StockMovementType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;

@RestController
public class StockMovementController {

    @Autowired
    private StockMovementService service;

    @GetMapping(value = "/stock_movements/{id}")
    public ResponseEntity<StockMovementDTO> findById(@PathVariable Long id) {
        StockMovementDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/products/{productId}/stock_movements/{type:in|out}")
    public ResponseEntity<StockMovementDTO> insert(
            @PathVariable Long productId,
            @PathVariable String type,
            @Valid @RequestBody StockMovementRequestDTO request) {
        StockMovementDTO dto = service.insert(productId, request, StockMovementType.valueOf(type.toUpperCase()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/stock_movements/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping(value = "/stock_movements/{id}/reversal")
    public ResponseEntity<StockMovementDTO> reverse(@PathVariable Long id) {
        StockMovementDTO dto = service.reverse(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/stock_movements/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
