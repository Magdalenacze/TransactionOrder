package com.example.transactionorder;

import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.exception.ProductException;
import com.example.transactionorder.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productServiceSuT;

    @Test
    void should_add_product_successfully() {
        // given
        ProductDto exampleProduct = new ProductDto("AddProduct", 3);

        // when
        Throwable thrown = catchThrowable(() -> productServiceSuT.addProduct(exampleProduct));

        // then
        assertThat(thrown).doesNotThrowAnyException();
    }

    @Test
    void should_not_allow_add_product_without_given_a_quantity() {
        // given
        ProductDto exampleProduct = new ProductDto("AddProduct", 0);

        // when
        Throwable thrown = catchThrowable(() -> productServiceSuT.addProduct(exampleProduct));

        // then
        assertThat(thrown)
                .isInstanceOf(ProductException.class)
                .hasMessageContaining("Product quantity must not be null!");
    }

    @Test
    void should_not_allow_delete_product_that_does_not_exist() {
        // given
        ProductDto exampleProduct = new ProductDto("Product", 1);
        productServiceSuT.addProduct(exampleProduct);

        // when
        Throwable thrown = catchThrowable(() -> productServiceSuT.deleteProductOutOfStock("OtherProduct"));

        // then
        assertThat(thrown)
                .isInstanceOf(ProductException.class)
                .hasMessageContaining("Sorry, this product does not exist!");
    }
}
