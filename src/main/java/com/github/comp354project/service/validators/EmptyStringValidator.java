package com.github.comp354project.service.validators;

import com.github.comp354project.service.exceptions.ValidationError;
import com.google.common.base.Strings;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EmptyStringValidator implements IUsernameValidator, IPasswordValidator {
    @Inject
    public EmptyStringValidator(){}

    @Override
    public List<ValidationError> validateUsername(String username, String message) {
        List<ValidationError> errors = new ArrayList<>();
        if(Strings.isNullOrEmpty(username)){
            errors.add(ValidationError.builder()
            .message(message)
            .parameterName("username")
            .parameterValue(username).build());
        }
        return errors;
    }

    @Override
    public List<ValidationError> validatePassword(String password, String message) {
        List<ValidationError> errors = new ArrayList<>();
        if(Strings.isNullOrEmpty(password)){
            errors.add(ValidationError.builder()
                    .message(message)
                    .parameterName("password")
                    .parameterValue(password).build());
        }
        return errors;
    }
}
