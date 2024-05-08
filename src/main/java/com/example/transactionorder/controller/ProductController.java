package com.example.transactionorder.controller;

import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @GetMapping
    public List<ProductDto> getProducts(@RequestParam(required = false) ProductDto productDto) {
        return productService.getProducts();
    }
}
