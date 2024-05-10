package com.example.transactionorder.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String productName;

    private Integer quantityOrdered;

    public OrderEntity(String productName, Integer quantityOrdered) {
        this.productName = productName;
        this.quantityOrdered = quantityOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;
        return Objects.equals(productName, that.productName) && Objects.equals(quantityOrdered, that.quantityOrdered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, quantityOrdered);
    }
}
