package org.example.productservice.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record CategoryRequest(
        Long id,

        @NotEmpty(message = "Category field name is required")
        String name,
        String description,
        List<ProductRequest> productRequests
) {
}
