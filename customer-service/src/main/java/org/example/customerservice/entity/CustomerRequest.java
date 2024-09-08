package org.example.customerservice.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CustomerRequest(
        Long id,

        @NotEmpty(message = "Nick name is required")
        String nickName,

        @NotEmpty(message = "Password is required")
        String password,

        @NotEmpty(message = "First name is required")
        String firstName,

        @NotEmpty(message = "Last name is required")
        String lastName,

        @Email(message = "Wrong pattern for email")
        String email,

        @NotEmpty(message = "Customer role is required")
        String role
) {
}
