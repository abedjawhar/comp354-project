package com.github.comp354project.service.validators;

import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator implements IUserValidator {

    private IUsernameValidator usernameValidator;
    private IPasswordValidator passwordValidator;
    private INameValidator nameValidator;

    public UserValidator(IUsernameValidator usernameValidator, IPasswordValidator passwordValidator, INameValidator nameValidator){
        this.usernameValidator = usernameValidator;
        this.passwordValidator = passwordValidator;
        this.nameValidator = nameValidator;
    }

    @Override
    public List<ValidationError> validateUser(User user) {
        List<ValidationError> errors = new ArrayList<>();
        if(user == null){
            errors.add(ValidationError.builder().parameterName("user").message("Invalid user").build());
        } else {
            errors.addAll(usernameValidator.validateUsername(user.getUsername(), "Invalid username"));
            errors.addAll(passwordValidator.validatePassword(user.getPassword(), "Invalid password"));
            errors.addAll(nameValidator.validateName(user.getFirstName(), "Invalid first name"));
            errors.addAll(nameValidator.validateName(user.getLastName(), "Invalid last name"));
        }
        return errors;
    }
}
