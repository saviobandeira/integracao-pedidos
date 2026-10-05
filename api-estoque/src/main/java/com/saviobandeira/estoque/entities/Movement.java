package com.saviobandeira.estoque.entities;

import com.saviobandeira.estoque.entities.enums.MovementType;

import java.util.Objects;
import java.time.LocalDate;
import java.time.Instant;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.PrePersist;

@Entity
@Table(name = "tb_movement")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private LocalDate postingDate;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reversed_id", unique = true)
    private Movement reversed;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        this.createdAt = Instant.now();
    }

    protected Movement() {
    }

    public Movement(String orderNumber, LocalDate postingDate, Integer quantity, MovementType type, Product product) {
        this.orderNumber = orderNumber;
        this.postingDate = postingDate;
        this.quantity = quantity;
        this.type = type;
        this.product = product;
    }

    public Movement reverse() {
        Movement reversal = new Movement(
                this.orderNumber,
                LocalDate.now(),
                -(this.quantity),
                MovementType.REVERSAL,
                this.product
        );

        reversal.reversed = this;
        return reversal;
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

    public Movement getReversed() {
        return reversed;
    }

    public Product getProduct() {
        return product;
    }

    public Instant getCreatedAt() {
        return createdAt;
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
