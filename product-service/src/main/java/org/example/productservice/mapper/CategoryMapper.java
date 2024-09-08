package org.example.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.example.productservice.entity.Category;
import org.example.productservice.entity.CategoryRequest;
import org.example.productservice.entity.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryMapper {

    private final ProductMapper productMapper;

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .productResponses(
                        category.getProducts()
                                .stream().map(
                                        productMapper::toProductResponse
                                ).toList()
                )
                .build();
    }

    public Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .id(categoryRequest.id())
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .products(
                        categoryRequest.productRequests()
                                .stream().map(
                                        productMapper::toProduct
                                ).toList()
                )
                .build();
    }
}
