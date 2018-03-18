package com.github.comp354project.model.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidationError {
    private String message;
    private String parameterName;
    private String parameterValue;
}
