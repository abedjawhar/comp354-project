package com.github.comp354project.service.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;


public class InvalidParameterException extends RuntimeException {
    @Getter
    private Map<String, String> data;

    @Builder
    private InvalidParameterException(String message, Throwable cause, Map<String, String> data){
        super(message, cause);
        this.data = data;
    }
}
