package com.example.transactionorder.service;

import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.entity.ProductEntity;
import com.example.transactionorder.repository.ProductRepository;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository transactionOrderRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity(productDto.getName(), productDto.getQuantity());
        transactionOrderRepository.save(productEntity);
    }
}
