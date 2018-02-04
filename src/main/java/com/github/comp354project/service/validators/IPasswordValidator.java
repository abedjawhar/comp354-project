package com.github.comp354project.service.validators;

import com.github.comp354project.service.exceptions.ValidationError;

import java.util.List;

public interface IPasswordValidator {
    List<ValidationError> validatePassword(String password, String message);
}
