package com.example.transactionorder.service;

import com.example.transactionorder.dto.OrderDto;
import com.example.transactionorder.entity.OrderEntity;
import com.example.transactionorder.entity.ProductEntity;
import com.example.transactionorder.repository.OrderRepository;
import com.example.transactionorder.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements  OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public void addOrder(OrderDto orderDto) {
        try {
            OrderEntity orderEntity = new OrderEntity(orderDto.getProductName(), orderDto.getQuantityOrdered());
            if (checkThePossibilityOfCompletingTheOrder(orderEntity)) {
                updateStock(orderEntity);
                orderRepository.save(orderEntity);
            } else {
                throw new RuntimeException("Sorry, your order cannot be processed!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean checkThePossibilityOfCompletingTheOrder(OrderEntity orderEntity) {
        List<ProductEntity> products = productRepository.findAllByName(orderEntity.getProductName());
        if (products.isEmpty()) {
            return false;
        } else if (products.get(0).getQuantity() < orderEntity.getQuantityOrdered()) {
            return false;
        } else {
            return true;
        }
    }

    public void updateStock(OrderEntity orderEntity) {
        List<ProductEntity> products = productRepository.findAllByName(orderEntity.getProductName());
        products.get(0).setQuantity(products.get(0).getQuantity() - orderEntity.getQuantityOrdered());
        productRepository.save(products.get(0));
    }
}
