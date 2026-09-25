package com.saviobandeira.estoque.dto;

import com.saviobandeira.estoque.entities.StockMovement;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StockMovementDTO {

    private Long id;

    @NotBlank
    private String orderNumber;

    @NotNull
    @Positive
    private Integer quantity;
    private Date createdAt = new Date();

    public StockMovementDTO() {
    }

    public StockMovementDTO(Long id, String orderNumber, Integer quantity, Date createdAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public StockMovementDTO(StockMovement entity) {
        id = entity.getId();
        orderNumber = entity.getOrderNumber();
        quantity = entity.getQuantity();
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
}
