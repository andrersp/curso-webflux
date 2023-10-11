package com.example.webflux.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicateKeyException(
            DuplicateKeyException exc
    ) {

        List<FieldErrors> fieldErrors = new ArrayList<>();

        StandardError err = new StandardError(LocalDateTime.now(),
                "",
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                verifyDupKey(exc.getMessage()),
                fieldErrors
        );
        System.out.println(err.toString());
        System.out.println("Andre");

        return ResponseEntity.badRequest().body(
                Mono.just(err)
        );

    }

    @ExceptionHandler(WebExchangeBindException.class)
    ResponseEntity<Mono<StandardError>> validationError(WebExchangeBindException exc){
        List<FieldErrors> errors = exc.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new FieldErrors(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        StandardError err = new StandardError(
                LocalDateTime.now(),
                "",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                "",
                errors
        );

        return ResponseEntity.badRequest().body(Mono.just(err));
    }

    private String verifyDupKey(String message) {
        if (message.contains("email dup key")) {
            return "email duplicado";
        }

        return "registro duplicado";
    }
}
