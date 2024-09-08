package org.example.productservice.entity;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        String description,
        List<ProductResponse>productResponses
) {
}
