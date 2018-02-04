package com.github.comp354project.service.auth;

import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.service.user.User;
import com.github.comp354project.service.validators.IPasswordValidator;
import com.github.comp354project.service.validators.IUsernameValidator;
import com.github.comp354project.service.validators.ValidatorFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationService implements IAuthenticationService {

    private IUserService userService;

    private IUsernameValidator usernameValidator;

    private IPasswordValidator passwordValidator;

    @Inject
    public AuthenticationService(IUserService userService){
        this.userService = userService;
        this.usernameValidator = ValidatorFactory.usernameValidator();
        this.passwordValidator  = ValidatorFactory.passwordValidator();
    }

    @Override
    public User authenticate(String username, String password) throws ValidationException {
        List<ValidationError> validationErrors = new ArrayList<>();
        validationErrors.addAll(this.usernameValidator.validateUsername(username, "Invalid username"));
        validationErrors.addAll(this.passwordValidator.validatePassword(password, "Invalid password"));
        if(!validationErrors.isEmpty()){
            throw ValidationException.builder()
                    .message("Failed to authenticate user")
                    .errors(validationErrors).build();
        }

        User user = userService.getUser(username);
        if(user == null){
            throw ValidationException.builder()
                    .message("Failed to authenticate user. User not found")
                    .error(ValidationError.builder()
                            .message("Invalid username")
                            .parameterName("username")
                            .parameterValue(username).build())
                    .build();
        }

        if(!user.getPassword().equals(password)){
            throw ValidationException.builder()
                    .message("Failed to authenticate user. Incorrect password")
                    .error(ValidationError.builder()
                            .message("Incorrect password")
                            .parameterName("password")
                            .parameterValue(password).build())
                    .build();
        }
        return user;
    }
}
