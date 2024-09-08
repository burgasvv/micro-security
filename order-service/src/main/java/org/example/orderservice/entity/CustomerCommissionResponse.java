package org.example.orderservice.entity;

import lombok.Builder;

@Builder
public record CustomerCommissionResponse(
        Long id,
        CustomerResponse customerResponse,
        PurchasedProducts purchasedProducts,
        Double totalPrice
) {
}
