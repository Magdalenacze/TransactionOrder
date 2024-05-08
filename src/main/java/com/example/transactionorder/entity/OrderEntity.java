package com.example.transactionorder.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
