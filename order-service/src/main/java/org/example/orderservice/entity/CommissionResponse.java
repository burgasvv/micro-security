package org.example.orderservice.entity;

import lombok.Builder;

import java.util.List;

@Builder
public record CommissionResponse(
        Long id,
        CustomerResponse customerResponse,
        List<CommissionLineResponse>commissionLineResponses,
        Double totalPrice
) {
}
