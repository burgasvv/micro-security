package org.example.productservice.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record ProductRequest(
        Long id,

        @NotEmpty(message = "Product field name is required")
        String name,

        @NotEmpty(message = "Product field description is required")
        String description,

        @NotEmpty(message = "Product field price is required")
        Double price,

        @NotEmpty(message = "Product field amount is required")
        Integer amount,

        String categoryName
) {
}
