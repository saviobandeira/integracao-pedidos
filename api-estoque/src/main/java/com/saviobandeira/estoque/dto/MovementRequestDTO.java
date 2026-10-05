package com.saviobandeira.estoque.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;

public class MovementRequestDTO {

    @NotBlank(message = "Número do pedido é obrigatório")
    private String orderNumber;

    @NotNull(message = "Data de emissão não pode ser nula")
    @PastOrPresent(message = "Data de emissão não pode ser uma data futura")
    private LocalDate postingDate;

    @NotNull(message = "Quantidade é obrigatório")
    @Positive(message = "Quantidade não pode ser igual ou menor que zero")
    private Integer quantity;

    public MovementRequestDTO(String orderNumber, LocalDate postingDate, Integer quantity) {
        this.orderNumber = orderNumber;
        this.postingDate = postingDate;
        this.quantity = quantity;
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
}