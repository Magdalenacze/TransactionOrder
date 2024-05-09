package com.example.transactionorder;

import com.example.transactionorder.dto.OrderDto;
import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.exception.OrderException;
import com.example.transactionorder.service.OrderService;
import com.example.transactionorder.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderServiceSuT;

    @Autowired
    private ProductService productServiceSuT;

    @Test
    public void should_add_order_successfully() {
        // given
        ProductDto exampleProduct = new ProductDto("AddProduct", 3);
        productServiceSuT.addProduct(exampleProduct);
        OrderDto exampleOrder = new OrderDto("AddProduct", 3);

        // when
        Throwable thrown = catchThrowable(() -> orderServiceSuT.addOrder(exampleOrder));

        // then
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    void should_not_allow_add_order_with_product_that_does_not_exist() {
        // given
        OrderDto exampleOrder = new OrderDto("AddProduct", 3);

        // when
        Throwable thrown = catchThrowable(() -> orderServiceSuT.addOrder(exampleOrder));

        // then
        assertThat(thrown)
                .isInstanceOf(OrderException.class)
                .hasMessageContaining("Sorry, your order cannot be processed due to " +
                        "the product being out of stock!");
    }
}
