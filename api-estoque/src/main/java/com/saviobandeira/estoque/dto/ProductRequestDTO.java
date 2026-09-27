package com.saviobandeira.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductRequestDTO {

    @NotBlank(message = "Código é obrigatório")
    private String code;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Positive(message = "Preço deve ser maior que zero")
    private Double price;

    public ProductRequestDTO(String code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
