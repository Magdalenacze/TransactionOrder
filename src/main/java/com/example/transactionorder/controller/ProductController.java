package com.example.transactionorder.controller;

import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }
}
