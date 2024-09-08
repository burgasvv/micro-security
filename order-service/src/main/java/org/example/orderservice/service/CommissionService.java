package org.example.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.entity.*;
import org.example.orderservice.exception.CommissionNotFoundException;
import org.example.orderservice.mapper.CommissionMapper;
import org.example.orderservice.repository.CommissionLineRepository;
import org.example.orderservice.repository.CommissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class CommissionService {

    private final CommissionRepository commissionRepository;
    private final CommissionLineRepository commissionLineRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final CommissionMapper commissionMapper;

    public List<CommissionResponse> findAll() {
        return commissionRepository
                .findAll()
                .stream()
                .map(commissionMapper::toCommissionResponse)
                .toList();
    }

    public CommissionResponse findById(Long commissionId) {
        return commissionRepository
                .findById(commissionId)
                .map(commissionMapper::toCommissionResponse)
                .orElseThrow(
                        () -> new CommissionNotFoundException(
                                "Commission with id " + commissionId + " not found"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public CustomerCommissionResponse createCommission(
            List<ProductRequest> productRequests, Long customerId
    ) {
        CustomerResponse customerResponse = customerClient.getCustomerById(customerId).getBody();
        PurchasedProducts purchasedProducts = productClient.getCustomerProducts(productRequests).getBody();

        double totalPrice = 0.0;
        for (
                ProductResponse productResponse : Objects
                .requireNonNull(purchasedProducts).purchasedProducts()
        ) {
            totalPrice = productResponse.price() * productResponse.amount();
        }

        Commission commission = commissionRepository.save(
                Commission.builder()
                        .customerId(customerId)
                        .totalPrice(totalPrice)
                        .build()
        );

        for (
                ProductResponse productResponse : Objects
                .requireNonNull(purchasedProducts).purchasedProducts()
        ) {
            commissionLineRepository.save(
                    CommissionLine.builder()
                            .productId(productResponse.id())
                            .productName(productResponse.name())
                            .amount(productResponse.amount())
                            .price(productResponse.price())
                            .commission(commission)
                            .build()
            );
        }

        return CustomerCommissionResponse.builder()
                .customerResponse(customerResponse)
                .purchasedProducts(purchasedProducts)
                .totalPrice(totalPrice)
                .build();
    }
}
