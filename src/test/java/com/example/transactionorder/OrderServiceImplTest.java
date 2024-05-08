//package com.example.transactionorder;
//
//import com.example.transactionorder.dto.OrderDto;
//import com.example.transactionorder.dto.ProductDto;
//import com.example.transactionorder.repository.OrderRepository;
//import com.example.transactionorder.repository.ProductRepository;
//import com.example.transactionorder.service.OrderService;
//import com.example.transactionorder.service.OrderServiceImpl;
//import com.example.transactionorder.service.ProductService;
//import com.example.transactionorder.service.ProductServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//
//public class OrderServiceImplTest {
//
//    private OrderService orderServiceSuT;
//    private ProductService productServiceSuT;
//    private ProductRepository productRepository;
//    private OrderRepository orderRepository;
//
//    @BeforeEach
//    void setUp() {
//        orderServiceSuT = new OrderServiceImpl(productRepository, orderRepository);
//        productServiceSuT = new ProductServiceImpl(productRepository);
//        productServiceSuT.addProduct(new ProductDto("Dress", 3));
//    }
//
//    @Test
//    public void should_not_add_order_successfully() {
//        //given
//        OrderDto orderDto = new OrderDto("Skirt", 1);
//
//        //when
//        Throwable thrown = catchThrowable(() -> orderServiceSuT.addOrder(orderDto));
//
//        //then
//        assertThat(thrown)
//                .isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("Sorry, your order cannot be processed!");
//    }
//}
