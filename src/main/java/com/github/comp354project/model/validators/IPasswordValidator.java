package com.github.comp354project.model.validators;

import com.github.comp354project.model.exceptions.ValidationError;

import java.util.List;

public interface IPasswordValidator {
    List<ValidationError> validatePassword(String password, String message);
}
