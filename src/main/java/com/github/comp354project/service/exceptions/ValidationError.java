package com.github.comp354project.service.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidationError {
    private String parameterName;
    private String parameterValue;
}
