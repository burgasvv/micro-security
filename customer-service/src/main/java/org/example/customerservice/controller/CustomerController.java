package org.example.customerservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.customerservice.entity.CustomerRequest;
import org.example.customerservice.entity.CustomerResponse;
import org.example.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(
                customerService.findAll()
        );
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable("customer-id") Long customerId
    ) {
        return ResponseEntity.ok(
                customerService.findById(customerId)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(
                customerService.create(customerRequest)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(
                customerService.update(customerRequest)
        );
    }

    @DeleteMapping("/delete/{customer-id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable("customer-id") Long customerId
    ) {
        return ResponseEntity.ok(
                customerService.deleteById(customerId)
        );
    }
}
