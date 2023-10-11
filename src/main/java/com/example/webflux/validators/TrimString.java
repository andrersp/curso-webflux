package com.example.webflux.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = { TrimStringValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
public @interface TrimString {
    String message() default "field cannot be blank spaces";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};
}
