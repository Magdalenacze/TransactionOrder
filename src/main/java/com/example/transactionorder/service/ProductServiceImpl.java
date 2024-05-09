package com.example.transactionorder.service;

import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.entity.ProductEntity;
import com.example.transactionorder.exception.ProductException;
import com.example.transactionorder.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        try {
            ProductEntity productEntity = new ProductEntity(productDto.getName(), productDto.getQuantity());
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new ProductException(e.getMessage());
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(e -> new ProductDto(e.getName(), e.getQuantity()))
                .toList();
    }
}
