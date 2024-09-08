package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.entity.Product;
import org.example.productservice.entity.ProductRequest;
import org.example.productservice.entity.ProductResponse;
import org.example.productservice.entity.PurchasedProducts;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.mapper.ProductMapper;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> findAll() {
        return productRepository
                .findAll()
                .stream().map(
                        productMapper::toProductResponse
                ).toList();
    }

    public ProductResponse findByName(String productName) {
        return productRepository
                .findByName(productName)
                .map(
                        productMapper::toProductResponse
                ).orElseThrow(
                        () -> new ProductNotFoundException(
                                "Product with name " + productName + "not found"
                        )
                );
    }

    public ProductResponse findById(Long productId) {
        return productRepository
                .findById(productId)
                .map(
                        productMapper::toProductResponse
                ).orElseThrow(
                        () -> new ProductNotFoundException(
                                "Product with id " + productId + "not found"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductResponse save(ProductRequest productRequest) {
        return productMapper.toProductResponse(
                productRepository.save(
                        productMapper.toProduct(productRequest)
                )
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public ProductResponse update(ProductRequest productRequest) {
        return productMapper.toProductResponse(
                productRepository.save(
                        productMapper.toProduct(productRequest)
                )
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public String deleteById(Long productId) {
        productRepository.deleteById(productId);
        return "Product with id " + productId + " successfully deleted";
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public PurchasedProducts offerProducts(List<ProductRequest> productRequests) {
        List<ProductResponse> all = findAll();
        List<ProductResponse> purchasedProducts = new ArrayList<>();
        List<ProductResponse> outOfStock = new ArrayList<>();

        for (ProductResponse productResponse : all) {
            for (ProductRequest productRequest : productRequests) {

                if (productResponse.id().equals(productRequest.id())) {

                    if (productRequest.amount() > productResponse.amount()) {
                        outOfStock.add(
                                ProductResponse.builder()
                                        .id(productResponse.id())
                                        .amount(productRequest.amount() - productResponse.amount())
                                        .price(productResponse.price())
                                        .name(productResponse.name())
                                        .description(productResponse.description())
                                        .categoryName(productResponse.categoryName())
                                        .build()
                        );
                        if (productResponse.amount() != 0) {
                            purchasedProducts.add(
                                    ProductResponse.builder()
                                            .id(productResponse.id())
                                            .amount(productResponse.amount())
                                            .price(productResponse.price())
                                            .name(productResponse.name())
                                            .description(productResponse.description())
                                            .categoryName(productResponse.categoryName())
                                            .build()
                            );
                        }
                        Product product = productRepository.findById(productRequest.id()).orElse(null);
                        if (product != null) {
                            product.setAmount(productRequest.amount() - productResponse.amount());
                            productRepository.save(product);
                        }

                    } else {

                        Product product = productRepository.findById(productRequest.id()).orElse(null);
                        if (product != null) {
                            product.setAmount(productResponse.amount() - productRequest.amount());
                            productRepository.save(product);
                        }
                        purchasedProducts.add(
                                ProductResponse.builder()
                                        .id(productResponse.id())
                                        .amount(productRequest.amount())
                                        .price(productResponse.price())
                                        .name(productResponse.name())
                                        .description(productResponse.description())
                                        .categoryName(productResponse.categoryName())
                                        .build()
                        );
                    }

                }
            }
        }

        return PurchasedProducts.builder()
                .purchasedProducts(purchasedProducts)
                .outOfStock(outOfStock)
                .build();
    }
}
