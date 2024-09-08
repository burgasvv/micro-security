package org.example.productservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryNotFoundException extends RuntimeException {

    private final String message;
}
