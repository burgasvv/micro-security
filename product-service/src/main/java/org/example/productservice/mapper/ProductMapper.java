package org.example.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.example.productservice.entity.Product;
import org.example.productservice.entity.ProductRequest;
import org.example.productservice.entity.ProductResponse;
import org.example.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .amount(product.getAmount())
                .categoryName(
                        product.getCategory().getName()
                )
                .build();
    }

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .amount(productRequest.amount())
                .category(
                        categoryRepository
                                .findByName(productRequest.categoryName())
                                .orElse(null)
                )
                .build();
    }
}
