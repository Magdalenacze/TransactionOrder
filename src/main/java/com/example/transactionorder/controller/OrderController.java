package com.example.transactionorder.controller;

import com.example.transactionorder.dto.OrderDto;
import com.example.transactionorder.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestParam(required = false) OrderDto orderDto) {
        return orderService.getOrders();
    }
}
