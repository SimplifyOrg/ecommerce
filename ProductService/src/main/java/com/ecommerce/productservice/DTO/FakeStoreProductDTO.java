package com.ecommerce.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;
}
