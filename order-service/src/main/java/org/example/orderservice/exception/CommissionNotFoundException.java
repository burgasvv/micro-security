package org.example.orderservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommissionNotFoundException extends RuntimeException {

    private final String message;
}
