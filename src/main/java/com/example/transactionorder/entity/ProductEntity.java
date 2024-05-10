package com.example.transactionorder.entity;

import com.example.transactionorder.exception.OrderException;
import com.example.transactionorder.exception.ProductException;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    private void validate(String name, Integer quantity) {
        if (name == null || name.length() == 0) {
            throw new ProductException("Product name must not be blank!");
        }
        if (quantity == null || quantity.equals(0)) {
            throw new ProductException("Product quantity must not be null!");
        }
    }

    public void updateStock(Integer quantityOrdered) {
        if (this.quantity < quantityOrdered) {
            throw new OrderException("Sorry, this product is currently out of stock!");
        }
        this.quantity -= quantityOrdered;
    }

    public boolean isQualified() {
        return this.quantity == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }
}
