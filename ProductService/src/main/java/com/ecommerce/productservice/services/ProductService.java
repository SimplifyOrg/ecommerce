package com.ecommerce.productservice.services;

import com.ecommerce.productservice.DTO.GenericProductDTO;
import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    GenericProductDTO getProductById(Long id);
    List<GenericProductDTO> getAllProducts();
    GenericProductDTO deleteProductById(Long id);
    GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO);
    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
}
