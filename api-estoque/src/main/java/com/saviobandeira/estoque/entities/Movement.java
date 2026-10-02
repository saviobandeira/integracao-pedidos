package com.saviobandeira.estoque.entities;

import com.saviobandeira.estoque.entities.enums.MovementType;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "tb_movement")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private Integer quantity;

    private Date createdAt = new Date();

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Movement() {
    }

    public Movement(Long id, String orderNumber, Integer quantity, MovementType type, Date createdAt, Product product) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.type = type;
        this.product = product;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String order_number) {
        this.orderNumber = order_number;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Movement that = (Movement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
