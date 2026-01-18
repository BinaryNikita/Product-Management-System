package com.example.productcrud.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequest {


    // name, description, price, stock, sku
    @NotBlank
    @Size(min = 0, max = 100)
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    // value = "0.0" → the minimum allowed value is 0.0
    // inclusive = false → the value must be strictly greater than 0.0, not equal
    private BigDecimal price;

    @Min(0)
    private Integer stock;

    @Pattern(regexp = "^[A-Z0-9_-]{3,50}$", message = "SKU must be 3-50 chars, uppercase letters/numbers/_/-")
    private String sku;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

}
