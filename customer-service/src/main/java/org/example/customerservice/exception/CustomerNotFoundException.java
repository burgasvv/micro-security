package org.example.customerservice.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerNotFoundException extends RuntimeException {

    private final String message;
}
