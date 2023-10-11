package com.example.webflux.exceptions;

import java.time.LocalDateTime;
import java.util.List;


public record StandardError(
        LocalDateTime timestamp,
        String path,

        Integer status,

        String error,

        String message,

        List<FieldErrors> errors
) {
}
