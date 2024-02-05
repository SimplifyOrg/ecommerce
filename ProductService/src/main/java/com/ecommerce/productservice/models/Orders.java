package com.ecommerce.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Orders extends BaseModel{
    @ManyToMany
    private List<Product> products;
}
