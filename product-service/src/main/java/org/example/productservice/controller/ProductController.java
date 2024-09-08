package org.example.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.productservice.entity.ProductRequest;
import org.example.productservice.entity.ProductResponse;
import org.example.productservice.entity.PurchasedProducts;
import org.example.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(
                productService.findAll()
        );
    }

    @GetMapping("/{product-name}")
    public ResponseEntity<ProductResponse> getProductByName(
            @PathVariable("product-name") String productName
    ) {
        return ResponseEntity.ok(
                productService.findByName(productName)
        );
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable("product-id") Long productId
    ) {
        return ResponseEntity.ok(
                productService.findById(productId)
        );
    }

    @PostMapping("/customer-products")
    public ResponseEntity<PurchasedProducts> getCustomerProducts(
            @RequestBody List<ProductRequest> productRequests
    ) {
        return ResponseEntity.ok(
                productService.offerProducts(productRequests)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody ProductRequest productRequest
    ) {
        return ResponseEntity.ok(
                productService.save(productRequest)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest
    ) {
        return ResponseEntity.ok(
                productService.update(productRequest)
        );
    }

    @DeleteMapping("/delete/{product-id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable("product-id") Long productId
    ) {
        return ResponseEntity.ok(
                productService.deleteById(productId)
        );
    }
}
