package com.github.comp354project.model.validators;

import com.github.comp354project.model.exceptions.ValidationError;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

public class StringLengthValidator implements ICategoryNameValidator, IUsernameValidator, IPasswordValidator, INameValidator{
    private final int minLength;
    private final int maxLength;

    public StringLengthValidator(int minLength, int maxLength){
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public List<ValidationError> validateName(String name, String message) {
        return validate(name, "name", message);
    }

    @Override
    public List<ValidationError> validateCategory(String category, String message) {
        return validate(category, "category", message);
    }

    @Override
    public List<ValidationError> validatePassword(String password, String message) {
        return validate(password, "password", message);
    }

    @Override
    public List<ValidationError> validateUsername(String username, String message) {
        return validate(username, "username", message);
    }

    private List<ValidationError> validate(String string, String paramName, String message){
        List<ValidationError> errors = new ArrayList<>();
        if(string == null){
            errors.add(ValidationError.builder()
                    .parameterName(paramName)
                    .message(message)
                    .build());
        }
        else if(string.length() < minLength || string.length() > maxLength) {
            errors.add(ValidationError.builder()
                    .parameterName(paramName)
                    .parameterValue(string)
                    .message(message).build());
        }
        return errors;
    }
}
