package com.github.comp354project.service.validators;

import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.user.User;

import java.util.List;

public interface IUserValidator {
    List<ValidationError> validateUser(User user);
}
