package com.saviobandeira.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StockMovementRequestDTO {

    @NotBlank(message = "Número do pedido é obrigatório")
    private String orderNumber;

    @NotNull(message = "Quantidade é obrigatório")
    @Positive(message = "Quantidade não pode ser igual ou menor que zero")
    private Integer quantity;

    public StockMovementRequestDTO(String orderNumber, Integer quantity) {
        this.orderNumber = orderNumber;
        this.quantity = quantity;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }
}