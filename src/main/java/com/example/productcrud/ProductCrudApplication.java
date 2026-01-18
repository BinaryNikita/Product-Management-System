package com.example.productcrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main entry point for the Product CRUD application.
 *
 * Notes:
 * - We set createdAt/updatedAt manually in the service implementation.
 *  If you want automatic JPA auditing later, add:
 *  @EnableJpaAuditing
 *  and annotate entity fields with @CreatedDate / @LastModifiedDate.
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class ProductCrudApplication {

    private static final Logger log = LoggerFactory.getLogger(ProductCrudApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductCrudApplication.class, args);
    }

  
    @Bean
    public CommandLineRunner startupRunner() {
        return args -> {
            log.info("------------------------------------------------------");
            log.info("Product CRUD application started.");
            log.info("Swagger UI: http://localhost:8080/swagger-ui.html");
            log.info("OpenAPI JSON: http://localhost:8080/v3/api-docs");
            log.info("------------------------------------------------------");
        };
    }
}
