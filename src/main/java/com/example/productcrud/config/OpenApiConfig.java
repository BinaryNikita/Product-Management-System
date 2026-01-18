package com.example.productcrud.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI producOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Product CRUD API")
                        .description("CRUD API for products")
                        .version("v1.0"));
    }
}
