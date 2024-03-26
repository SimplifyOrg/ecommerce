package com.ecommerce.productservice.DTO;

import com.ecommerce.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDTO {
    private UUID id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;

    public static GenericProductDTO from(Product product) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(product.getId());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setCategory(String.valueOf(product.getCategory()));
        genericProductDTO.setDescription(product.getDescription());
        return genericProductDTO;
    }
}
