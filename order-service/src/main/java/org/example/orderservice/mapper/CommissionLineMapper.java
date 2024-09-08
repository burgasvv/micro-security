package org.example.orderservice.mapper;

import org.example.orderservice.entity.CommissionLine;
import org.example.orderservice.entity.CommissionLineResponse;
import org.springframework.stereotype.Service;

@Service
public class CommissionLineMapper {

    public CommissionLineResponse toCommissionLineResponse(CommissionLine commissionLine) {
        return CommissionLineResponse.builder()
                .id(commissionLine.getId())
                .productId(commissionLine.getProductId())
                .productName(commissionLine.getProductName())
                .price(commissionLine.getPrice())
                .amount(commissionLine.getAmount())
                .build();
    }

}
