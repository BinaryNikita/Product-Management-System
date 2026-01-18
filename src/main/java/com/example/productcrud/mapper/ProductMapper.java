package com.example.productcrud.mapper;

// Import all required MapStruct annotations
import org.mapstruct.*;
import com.example.productcrud.dto.ProductRequest;
import com.example.productcrud.dto.ProductResponse;
import com.example.productcrud.entity.Product;

/**
 * This interface tells MapStruct to generate mapping code that converts
 * between Product DTOs and the Product entity.
 */
@Mapper(componentModel = "spring") 
// @Mapper → Marks this interface for MapStruct processing
// componentModel = "spring" → MapStruct will generate a Spring Bean
// So you can @Autowired this mapper anywhere
public interface ProductMapper {

    /**
     * Converts ProductRequest DTO → Product Entity.
     * Used when creating a new Product in the database.
     */
    Product toEntity(ProductRequest dto);

    /**
     * Converts Product Entity → ProductResponse DTO.
     * Used when sending product data in API responses.
     */
    ProductResponse toResponse(Product entity);

    /**
     * Updates an existing Product entity with non-null values from the DTO.
     * Null fields in the DTO will NOT overwrite existing entity fields.
     * Perfect for update operations (PUT/PATCH).
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // IGNORE → Means "do not overwrite fields with null"
    void updateEntityFromDto(ProductRequest dto, @MappingTarget Product entity);
    // @MappingTarget → Tells MapStruct to update the existing entity object
}
