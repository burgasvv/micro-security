package org.example.customerservice.entity;

import lombok.Builder;

@Builder
public record CustomerResponse(
        Long id,
        String nickName,
        String password,
        String firstName,
        String lastName,
        String email,
        String role
) {
}
