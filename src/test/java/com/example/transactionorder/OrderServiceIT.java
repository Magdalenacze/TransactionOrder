package com.example.transactionorder;

import com.example.transactionorder.dto.OrderDto;
import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.entity.OrderEntity;
import com.example.transactionorder.entity.ProductEntity;
import com.example.transactionorder.exception.OrderException;
import com.example.transactionorder.repository.OrderRepository;
import com.example.transactionorder.repository.ProductRepository;
import com.example.transactionorder.service.OrderService;
import com.example.transactionorder.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
public class OrderServiceIT {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void beforeEach() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    void should_add_order_with_product_in_stock() {
        // given
        ProductDto exampleProduct = new ProductDto("AddProduct", 3);
        productService.addProduct(exampleProduct);
        OrderDto exampleOrder = new OrderDto("AddProduct", 1);
        OrderEntity referenceOrderEntity = new OrderEntity(
                exampleOrder.getProductName(),
                exampleOrder.getQuantityOrdered());

        // when
        orderService.addOrder(exampleOrder);

        // then
        List<OrderEntity> entities = orderRepository.findAll();
        assertThat(entities).hasSize(1);

        OrderEntity orderEntity = entities.get(0);
        assertThat(orderEntity).isEqualTo(referenceOrderEntity);
    }

    @Test
    void should_not_allow_add_order_with_product_out_of_stock() {
        // given
        ProductDto exampleProduct = new ProductDto("AddProduct", 1);
        productService.addProduct(exampleProduct);
        OrderDto exampleOrder = new OrderDto("AddProduct", 3);

        // when
        Throwable thrown = catchThrowable(() -> orderService.addOrder(exampleOrder));

        // then
        assertThat(thrown)
                .isInstanceOf(OrderException.class)
                .hasMessageContaining("Sorry, this product is currently out of stock!");
    }

    @Test
    void should_get_order_successfully() {
        // given
        ProductDto exampleProduct = new ProductDto("GetProduct", 3);
        productService.addProduct(exampleProduct);
        OrderDto exampleOrder = new OrderDto("GetProduct", 1);
        orderService.addOrder(exampleOrder);

        // when
        List<OrderDto> orders = orderService.getOrders();

        // then
        assertThat(orders).containsExactlyInAnyOrder(exampleOrder);
    }

    @Test
    void should_delete_unavailable_product_successfully() {
        // given
        ProductDto exampleProduct = new ProductDto("AddProduct", 1);
        productService.addProduct(exampleProduct);
        OrderDto exampleOrder = new OrderDto("AddProduct", 1);

        // when
        orderService.addOrder(exampleOrder);

        // then
        List<ProductEntity> entities = productRepository.findAll();
        assertThat(entities).hasSize(0);
    }
}
