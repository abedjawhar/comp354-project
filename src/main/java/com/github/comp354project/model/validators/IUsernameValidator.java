package com.github.comp354project.model.validators;

import com.github.comp354project.model.exceptions.ValidationError;

import java.util.List;

public interface IUsernameValidator {
    List<ValidationError> validateUsername(String username, String message);
}
