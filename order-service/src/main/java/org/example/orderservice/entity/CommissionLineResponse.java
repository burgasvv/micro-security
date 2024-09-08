package org.example.orderservice.entity;

import lombok.Builder;

@Builder
public record CommissionLineResponse(
        Long id,
        Long productId,
        String productName,
        Integer amount,
        Double price
) {
}
