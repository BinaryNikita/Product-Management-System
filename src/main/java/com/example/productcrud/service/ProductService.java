package com.example.productcrud.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import com.example.productcrud.dto.ProductRequest;
import com.example.productcrud.dto.ProductResponse;




public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse getById(UUID id);

    Page<ProductResponse> list(String nameFilter, int page, int size, String sort);

    ProductResponse update(UUID id, ProductRequest request);

    void delete(UUID id);

}