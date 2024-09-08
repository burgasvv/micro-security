package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.entity.CommissionResponse;
import org.example.orderservice.entity.CustomerCommissionResponse;
import org.example.orderservice.entity.ProductRequest;
import org.example.orderservice.service.CommissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commissions")
public class CommissionController {

    private final CommissionService commissionService;

    @GetMapping
    public ResponseEntity<List<CommissionResponse>>getCommissions() {
        return ResponseEntity.ok(
                commissionService.findAll()
        );
    }

    @GetMapping("/{commission-id}")
    public ResponseEntity<CommissionResponse> getCommissionById(
            @PathVariable("commission-id") Long commissionId
    ) {
        return ResponseEntity.ok(
                commissionService.findById(commissionId)
        );
    }

    @PostMapping("/create-commission/{customer-id}")
    public ResponseEntity<CustomerCommissionResponse> createCommission(
            @RequestBody List<ProductRequest> productRequests,
            @PathVariable("customer-id") Long customerId
    ) {
        return ResponseEntity.ok(
                commissionService.createCommission(productRequests, customerId)
        );
    }
}
