package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.models.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByTitleContainingIgnoreCase(String query, PageRequest pageRequest);
}
