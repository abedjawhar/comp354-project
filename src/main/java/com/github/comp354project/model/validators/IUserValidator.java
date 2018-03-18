package com.github.comp354project.model.validators;

import com.github.comp354project.model.exceptions.ValidationError;
import com.github.comp354project.model.user.User;

import java.util.List;

public interface IUserValidator {
    List<ValidationError> validateUser(User user);
}
