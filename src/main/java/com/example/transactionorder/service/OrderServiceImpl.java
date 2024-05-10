package com.example.transactionorder.service;

import com.example.transactionorder.dto.OrderDto;
import com.example.transactionorder.entity.OrderEntity;
import com.example.transactionorder.exception.OrderException;
import com.example.transactionorder.repository.OrderRepository;
import com.example.transactionorder.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public void addOrder(OrderDto orderDto) {
        OrderEntity orderEntity = productRepository.findByName(orderDto.getProductName())
                .map(e -> {
                    e.updateStock(orderDto.getQuantityOrdered());
                    return new OrderEntity(orderDto.getProductName(), orderDto.getQuantityOrdered());
                }).orElseThrow(() -> new OrderException(
                        "Sorry, your order cannot be processed due to the product being out of stock!"));
        productService.deleteProductOutOfStock(orderDto.getProductName());
        orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(e -> new OrderDto(e.getProductName(), e.getQuantityOrdered()))
                .toList();
    }
}