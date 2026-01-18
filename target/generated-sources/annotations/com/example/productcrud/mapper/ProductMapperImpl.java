package com.example.productcrud.mapper;

import com.example.productcrud.dto.ProductRequest;
import com.example.productcrud.dto.ProductResponse;
import com.example.productcrud.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-18T07:41:02+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequest dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setSku( dto.getSku() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setStock( dto.getStock() );

        return product;
    }

    @Override
    public ProductResponse toResponse(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( entity.getId() );
        productResponse.setName( entity.getName() );
        productResponse.setDescription( entity.getDescription() );
        productResponse.setPrice( entity.getPrice() );
        productResponse.setStock( entity.getStock() );
        productResponse.setSku( entity.getSku() );
        productResponse.setCreatedAt( entity.getCreatedAt() );
        productResponse.setUpdatedAt( entity.getUpdatedAt() );

        return productResponse;
    }

    @Override
    public void updateEntityFromDto(ProductRequest dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getSku() != null ) {
            entity.setSku( dto.getSku() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getStock() != null ) {
            entity.setStock( dto.getStock() );
        }
    }
}
