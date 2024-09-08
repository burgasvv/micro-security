package org.example.orderservice.service;

import org.example.orderservice.entity.ProductRequest;
import org.example.orderservice.entity.PurchasedProducts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8765/products"
)
public interface ProductClient {

    @PostMapping("/customer-products")
    ResponseEntity<PurchasedProducts> getCustomerProducts(
            @RequestBody List<ProductRequest> productRequests
    );
}
