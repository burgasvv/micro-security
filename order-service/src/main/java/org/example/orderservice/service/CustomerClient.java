package org.example.orderservice.service;

import org.example.orderservice.entity.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8765/customers"
)
public interface CustomerClient {

    @GetMapping("/{customer-id}")
    ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable("customer-id") Long customerId
    );
}
