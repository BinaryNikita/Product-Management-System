package com.example.productcrud.service.impl;

import java.time.Instant;
import java.util.UUID;
 

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.productcrud.dto.ProductRequest;
import com.example.productcrud.dto.ProductResponse;
import com.example.productcrud.entity.Product;
import com.example.productcrud.exception.ResourceNotFoundException;
import com.example.productcrud.exception.DuplicateResourceException;
import com.example.productcrud.mapper.ProductMapper;
import com.example.productcrud.repository.ProductRepository;
import com.example.productcrud.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse create(ProductRequest request) {

        if (repository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException("Product with sku: "+ request.getSku() +" already exists");
        }
        Product product = mapper.toEntity(request);
        Instant now = Instant.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        Product saved = repository.save(product);
        return mapper.toResponse(saved);
    }

    @Override
    public ProductResponse getById(UUID id) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));

        return mapper.toResponse(p);
    }

    @Override
    public Page<ProductResponse> list(String nameFilter, int page, int size, String sort) {
        Sort sortObj = Sort.by("id").ascending();
        if (sort != null && !sort.isBlank()) {
            String[] pieces = sort.split(",");
            if (pieces.length == 2) {
                sortObj = Sort.by(Sort.Direction.fromString(pieces[1]), pieces[2]);
            } else {
                sortObj = Sort.by(sort);
            }
        }

        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Product> results;
        if (nameFilter != null && !nameFilter.isBlank()) {
            results = repository.findByNameContainingIgnoreCase(nameFilter, pageable);
        } else {
            results = repository.findAll(pageable);
        }

        return results.map(mapper::toResponse);

    }

    @Override
    public ProductResponse update(UUID id, ProductRequest request) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        mapper.updateEntityFromDto(request, existing);
        existing.setUpdatedAt(Instant.now());
        Product saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found: " + id);
        }
        repository.deleteById(id);
    }

}