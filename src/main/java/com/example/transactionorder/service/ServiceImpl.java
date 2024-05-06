package com.example.transactionorder.service;

import com.example.transactionorder.model.Product;
import com.example.transactionorder.repository.Repository;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceImpl implements Service {

    private final Repository repository;

    @Override
    public void addProduct(Product product) {
        repository.addProduct(product);
    }
}
