package com.saviobandeira.estoque.controllers;

import com.saviobandeira.estoque.services.MovementService;
import com.saviobandeira.estoque.dto.MovementDTO;
import com.saviobandeira.estoque.dto.MovementRequestDTO;
import com.saviobandeira.estoque.entities.enums.MovementType;

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
public class MovementController {

    @Autowired
    private MovementService service;

    @GetMapping(value = "/movements/{id}")
    public ResponseEntity<MovementDTO> findById(@PathVariable Long id) {
        MovementDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/products/{productId}/movements/{type:in|out}")
    public ResponseEntity<MovementDTO> insert(
            @PathVariable Long productId,
            @PathVariable String type,
            @Valid @RequestBody MovementRequestDTO request) {
        MovementDTO dto = service.insert(productId, request, MovementType.valueOf(type.toUpperCase()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/movements/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping(value = "/movements/{id}/reversal")
    public ResponseEntity<MovementDTO> reverse(@PathVariable Long id) {
        MovementDTO dto = service.reverse(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/movements/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
