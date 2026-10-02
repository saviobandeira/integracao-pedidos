package com.saviobandeira.estoque.dto;

import com.saviobandeira.estoque.entities.Movement;
import com.saviobandeira.estoque.entities.enums.MovementType;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MovementDTO {

    private Long id;

    @NotBlank(message = "Número do pedido é obrigatório")
    private String orderNumber;

    @NotNull(message = "Tipo não pode ser nulo")
    private MovementType type;

    @NotNull(message = "Quantidade é obrigatório")
    @Positive(message = "Quantidade não pode ser igual ou menor que zero")
    private Integer quantity;
    private Date createdAt = new Date();

    @NotNull(message = "Produto não pode ser nulo")
    private ProductDTO product;

    public MovementDTO() {
    }

    public MovementDTO(Long id, String orderNumber, Integer quantity, MovementType type, ProductDTO product, Date createdAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.type = type;
        this.product = product;
        this.createdAt = createdAt;
    }

    public MovementDTO(Movement entity) {
        id = entity.getId();
        orderNumber = entity.getOrderNumber();
        quantity = entity.getQuantity();
        type = entity.getType();
        product = new ProductDTO(entity.getProduct());
        createdAt = entity.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public MovementType getType() {
        return type;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
