package com.ecommerce.productservice.services;

import com.ecommerce.productservice.DTO.GenericProductDTO;
import com.ecommerce.productservice.exceptions.ProductNotFound;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    GenericProductDTO getProductById(Long id) throws ProductNotFound;
    List<GenericProductDTO> getAllProducts();
    GenericProductDTO deleteProductById(Long id) throws ProductNotFound;
    GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) throws ProductNotFound;
    GenericProductDTO createProduct(GenericProductDTO genericProductDTO) throws Exception;
}
