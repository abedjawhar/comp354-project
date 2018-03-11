package com.github.comp354project.service.validators;

import com.github.comp354project.service.exceptions.ValidationError;

import java.util.List;

public interface INameValidator {
    List<ValidationError> validateName(String name, String message);
}
