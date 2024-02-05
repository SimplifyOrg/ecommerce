package com.ecommerce.productservice;


import com.ecommerce.productservice.controllers.ProductController;
import com.ecommerce.productservice.exceptions.ProductNotFound;
import com.ecommerce.productservice.services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerUnitTest {

    @Inject
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProductById() {
        assertThrows(ProductNotFound.class, () -> productController.getProductById(1000L));
    }

    @Test
    void testProductServiceMockGetId() throws ProductNotFound {
        when(productService.getProductById(100L)).thenReturn(null);
        assertNull(productController.getProductById(100L));
    }
}
