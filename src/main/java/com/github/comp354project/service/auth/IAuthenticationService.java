package com.github.comp354project.service.auth;

import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;

public interface IAuthenticationService {
    /**
     * Attempt to authenticate the user
     * @param username
     * @param password
     * @return User being authenticated
     * @throws ValidationException If the username and/or password are invalid/nonexistent/incorrect
     */
    User authenticate(String username, String password) throws ValidationException;
}
