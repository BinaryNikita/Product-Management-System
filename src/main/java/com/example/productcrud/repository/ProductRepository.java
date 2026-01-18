package com.example.productcrud.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productcrud.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

    Page<Product> findByNameContainingIgnoreCase(String product, Pageable pageable);
    boolean existsBySku(String sku);
}
    

