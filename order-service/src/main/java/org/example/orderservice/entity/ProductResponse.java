package org.example.orderservice.entity;

import lombok.Builder;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Integer amount,
        String categoryName
) {
}
