package com.github.comp354project.service.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private List<ValidationError> errors;

    @Builder
    private ValidationException(String message, Throwable cause, @Singular List<ValidationError> errors){
        super(message, cause);
        this.errors = errors;
    }
}
