package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.entity.CategoryRequest;
import org.example.productservice.entity.CategoryResponse;
import org.example.productservice.exception.CategoryNotFoundException;
import org.example.productservice.mapper.CategoryMapper;
import org.example.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse findByName(String categoryName) {
        //noinspection preview
        return categoryRepository
                .findByName(categoryName)
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                STR."Category with name \{categoryName}not found"
                        )
                );
    }

    public CategoryResponse findById(Long categoryId) {
        //noinspection preview
        return categoryRepository
                .findById(categoryId)
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                STR."Category with id \{categoryId}not found"
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public CategoryResponse create(CategoryRequest categoryRequest) {
        return categoryMapper
                .toCategoryResponse(
                        categoryRepository.save(
                                categoryMapper.toCategory(categoryRequest)
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public CategoryResponse update(CategoryRequest categoryRequest) {
        return categoryMapper
                .toCategoryResponse(
                        categoryRepository.save(
                                categoryMapper.toCategory(categoryRequest)
                        )
                );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED
    )
    public String delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        //noinspection preview
        return STR."Category with id \{categoryId}successfully deleted";
    }
}
