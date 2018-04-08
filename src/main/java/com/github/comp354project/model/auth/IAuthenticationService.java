package com.github.comp354project.model.auth;

import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import com.github.comp354project.utils.Timing;

@Timing
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
