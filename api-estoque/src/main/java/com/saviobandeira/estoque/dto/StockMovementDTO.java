package com.saviobandeira.estoque.dto;

import com.saviobandeira.estoque.entities.StockMovement;
import com.saviobandeira.estoque.entities.enums.StockMovementType;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StockMovementDTO {

    private Long id;

    @NotBlank(message = "Número do pedido é obrigatório")
    private String orderNumber;

    @NotNull(message = "Tipo não pode ser nulo")
    private StockMovementType type;

    @NotNull(message = "Quantidade é obrigatório")
    @Positive(message = "Quantidade não pode ser igual ou menor que zero")
    private Integer quantity;
    private Date createdAt = new Date();

    @NotNull(message = "Produto não pode ser nulo")
    private ProductDTO product;

    public StockMovementDTO() {
    }

    public StockMovementDTO(Long id, String orderNumber, Integer quantity, StockMovementType type, ProductDTO product, Date createdAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.type = type;
        this.product = product;
        this.createdAt = createdAt;
    }

    public StockMovementDTO(StockMovement entity) {
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

    public StockMovementType getType() {
        return type;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
