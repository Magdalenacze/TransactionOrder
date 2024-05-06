package com.example.transactionorder.dto;

import com.example.transactionorder.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(Product product) {
        return new Product(product.getId(), product.getName(), product.getQuantity());
    }
}
