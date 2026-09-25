package com.saviobandeira.estoque.dto;

import com.saviobandeira.estoque.entities.Product;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Código é obrigatório")
    private String code;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotNull(message = "Preço não pode ser nulo")
    @Positive(message = "Preço deve ser maior que zero ")
    private Double price;

    @NotNull(message = "Saldo não pode ser nulo")
    @PositiveOrZero(message = "Saldo deve ser maior ou igual que zero")
    private Integer balance;
    private Boolean active = true;
    private Date created_at = new Date();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String code, String name, Double price, Integer balance, Boolean active, Date created_at) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.balance = balance;
        this.active = active;
        this.created_at = created_at;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        code = entity.getCode();
        name = entity.getName();
        price = entity.getPrice();
        balance = entity.getBalance();
        active = entity.getActive();
        created_at = entity.getCreated_at();
    }

    public Long getId() {
        return id;
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

    public Integer getBalance() {
        return balance;
    }

    public Boolean getActive() {
        return active;
    }

    public Date getCreated_at() {
        return created_at;
    }
}
