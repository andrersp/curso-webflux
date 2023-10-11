package com.example.webflux.model.request;

import com.example.webflux.validators.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "not be empty")
        @Size(min = 3, max = 50)
        @TrimString
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 6)
        String password
) {
}
