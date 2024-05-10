package com.example.transactionorder;

import com.example.transactionorder.dto.ProductDto;
import com.example.transactionorder.entity.ProductEntity;
import com.example.transactionorder.exception.ProductException;
import com.example.transactionorder.repository.ProductRepository;
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
public class ProductServiceIT {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
        productRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void should_add_product_successfully() {
        // given
        ProductDto exampleProduct = new ProductDto("AddProduct", 3);
        ProductEntity referenceEntity = new ProductEntity(
                exampleProduct.getName(),
                exampleProduct.getQuantity());

        // when
        productService.addProduct(exampleProduct);

        // then
        List<ProductEntity> entities = productRepository.findAll();
        assertThat(entities).hasSize(1);

        ProductEntity productEntity = entities.get(0);
        assertThat(productEntity).isEqualTo(referenceEntity);
    }

    @Test
    void should_not_allow_add_product_without_given_a_name() {
        // given
        ProductDto exampleProduct = new ProductDto("", 3);

        // when
        Throwable thrown = catchThrowable(() -> productService.addProduct(exampleProduct));

        // then
        assertThat(thrown)
                .isInstanceOf(ProductException.class)
                .hasMessageContaining("Product name must not be blank!");
    }

    @Test
    void should_get_product_successfully() {
        // given
        ProductDto exampleProduct = new ProductDto("GetProduct", 3);
        productService.addProduct(exampleProduct);

        // when
        List<ProductDto> products = productService.getProducts();

        // then
        assertThat(products).containsExactlyInAnyOrder(exampleProduct);
    }
}
