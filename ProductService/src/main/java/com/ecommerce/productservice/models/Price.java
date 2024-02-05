package com.ecommerce.productservice.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Price extends BaseModel{
    private String Currency;
    private double value;
}
