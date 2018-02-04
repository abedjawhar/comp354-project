package com.github.comp354project.service.validators;

import com.github.comp354project.service.exceptions.ValidationError;

import java.util.List;

public interface IUsernameValidator {
    List<ValidationError> validateUsername(String username, String message);
}
