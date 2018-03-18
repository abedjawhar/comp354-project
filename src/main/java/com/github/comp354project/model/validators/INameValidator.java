package com.github.comp354project.model.validators;

import com.github.comp354project.model.exceptions.ValidationError;

import java.util.List;

public interface INameValidator {
    List<ValidationError> validateName(String name, String message);
}
