package com.example.webflux.exceptions;

public record FieldErrors(
        String fieldError,
        String message
) {
}
