package com.example.transactionorder.controller;

import com.example.transactionorder.model.Product;
import com.example.transactionorder.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class Controller {

    private final Service service;

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        service.addProduct(product);
    }
}
