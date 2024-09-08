package org.example.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.productservice.entity.CategoryRequest;
import org.example.productservice.entity.CategoryResponse;
import org.example.productservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(
                categoryService.findAll()
        );
    }

    @GetMapping("/{category-name}")
    public ResponseEntity<CategoryResponse> getCategoryByName(
            @PathVariable("category-name") String categoryName
    ) {
        return ResponseEntity.ok(
                categoryService.findByName(categoryName)
        );
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable("category-id") Long categoryId
    ) {
        return ResponseEntity.ok(
                categoryService.findById(categoryId)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody CategoryRequest categoryRequest
    ) {
        return ResponseEntity.ok(
                categoryService.create(categoryRequest)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryResponse> updateCategory(
            @RequestBody CategoryRequest categoryRequest
    ) {
        return ResponseEntity.ok(
                categoryService.update(categoryRequest)
        );
    }

    @DeleteMapping("/delete/{category-id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable("category-id") Long categoryId
    ) {
        return ResponseEntity.ok(
                categoryService.delete(categoryId)
        );
    }
}
