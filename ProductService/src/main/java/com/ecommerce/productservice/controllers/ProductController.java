package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.DTO.GenericProductDTO;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    final private ProductService productService;

    ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) {
        return this.productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id) {
        return this.productService.deleteProductById(id);
    }


    public GenericProductDTO updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDTO genericProductDTO) {
        return this.productService.updateProductById(id, genericProductDTO);
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO) {
        return this.productService.createProduct(genericProductDTO);
    }
}
