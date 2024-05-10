package com.example.transactionorder.service;

import com.example.transactionorder.dto.ProductDto;

import java.util.List;

public interface ProductService {

    void addProduct(ProductDto productDto);

    List<ProductDto> getProducts();

    void deleteProductOutOfStock(String name);
}
