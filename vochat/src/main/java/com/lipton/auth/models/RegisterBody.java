package com.lipton.auth.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterBody(
        @NotBlank(message = "Username not set")
        @Size(min = 3, max = 255, message = "Username must not exceed 255 characters")
        String username,
        @NotBlank(message = "Password not set")
        String password,
        @NotBlank(message = "Password not set")
        String confirmPassword
) {
}
