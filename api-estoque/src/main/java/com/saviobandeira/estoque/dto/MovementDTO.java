package com.saviobandeira.estoque.dto;

import com.saviobandeira.estoque.entities.Movement;
import com.saviobandeira.estoque.entities.enums.MovementType;

import java.time.LocalDate;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class MovementDTO {

    private Long id;

    @NotBlank(message = "Número do pedido é obrigatório")
    private String orderNumber;

    @NotNull(message = "Data de emissão não pode ser nula")
    @PastOrPresent(message = "Data de emissão não pode ser uma data futura")
    private LocalDate postingDate;

    @NotNull(message = "Quantidade é obrigatório")
    @Positive(message = "Quantidade não pode ser igual ou menor que zero")
    private Integer quantity;

    @NotNull(message = "Tipo não pode ser nulo")
    private MovementType type;

    private Long reversedId;

    @NotNull(message = "Produto não pode ser nulo")
    private ProductDTO product;

    private Instant createdAt;

    public MovementDTO() {
    }

    public MovementDTO(Long id, String orderNumber, LocalDate postingDate, Integer quantity, MovementType type, Long reversedId, ProductDTO product, Instant createdAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.postingDate = postingDate;
        this.quantity = quantity;
        this.type = type;
        this.reversedId = reversedId;
        this.product = product;
        this.createdAt = createdAt;
    }

    public MovementDTO(Movement entity) {
        id = entity.getId();
        orderNumber = entity.getOrderNumber();
        postingDate = entity.getPostingDate();
        quantity = entity.getQuantity();
        type = entity.getType();
        reversedId = entity.getReversed() != null? entity.getReversed().getId() : null;
        product = new ProductDTO(entity.getProduct());
        createdAt = entity.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MovementType getType() {
        return type;
    }

    public Long getReversed() {
        return reversedId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
