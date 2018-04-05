package com.github.comp354project.model.validators;

import com.github.comp354project.model.exceptions.ValidationError;

import java.util.List;

public interface IEmailValidator {
    List<ValidationError> validateEmail(String name, String message);
}
