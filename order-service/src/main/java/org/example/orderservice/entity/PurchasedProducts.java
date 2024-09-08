package org.example.orderservice.entity;

import lombok.Builder;

import java.util.List;

@Builder
public record PurchasedProducts(
        List<ProductResponse> purchasedProducts,
        List<ProductResponse>outOfStock
) {
}
