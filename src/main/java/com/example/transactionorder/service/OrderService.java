package com.example.transactionorder.service;

import com.example.transactionorder.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void addOrder(OrderDto orderDto);

    List<OrderDto> getOrders();
}
