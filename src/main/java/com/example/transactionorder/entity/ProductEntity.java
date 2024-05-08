package com.example.transactionorder.entity;

import com.example.transactionorder.exception.ProductException;
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

    @Column(unique = true)
    private String name;

    private Integer quantity;

    public ProductEntity(String name, Integer quantity) {
        validate(name, quantity);
        this.name = name;
        this.quantity = quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private void validate(String name, Integer quantity) {
        if (name == null || name.length() == 0) {
            throw new ProductException("Product name must not be blank!");
        }
        if (quantity == null || quantity.equals(0)) {
            throw new ProductException("Product quantity must not be null!");
        }
    }
}
