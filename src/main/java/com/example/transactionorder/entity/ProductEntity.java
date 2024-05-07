package com.example.transactionorder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    private Integer quantity;

    public ProductEntity(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
