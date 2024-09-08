package org.example.orderservice.mapper;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.entity.Commission;
import org.example.orderservice.entity.CommissionResponse;
import org.example.orderservice.service.CustomerClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommissionMapper {

    private final CustomerClient customerClient;
    private final CommissionLineMapper commissionLineMapper;

    public CommissionResponse toCommissionResponse(Commission commission) {
        return CommissionResponse.builder()
                .id(commission.getId())
                .customerResponse(
                        customerClient.getCustomerById(commission.getCustomerId()).getBody()
                ).commissionLineResponses(
                        commission.getCommissionLines()
                                .stream()
                                .map(commissionLineMapper::toCommissionLineResponse)
                                .toList()
                )
                .totalPrice(commission.getTotalPrice())
                .build();
    }
}
