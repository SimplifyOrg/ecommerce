package com.ecommerce.productservice.exceptions;

public class ProductNotFound extends Exception{
    public ProductNotFound(Long id) {
        super("Product with id " + id + " is not found");
    }
}
