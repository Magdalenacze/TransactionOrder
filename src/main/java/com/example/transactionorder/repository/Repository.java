package com.example.transactionorder.repository;

import com.example.transactionorder.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Product, Long> {

    void addProduct(Product product);
}
