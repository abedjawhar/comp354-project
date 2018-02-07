package com.github.comp354project.service.auth;

import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Manages the user's session, whether he's connected or not.
 */
@Singleton
public class SessionManager {

    private IAuthenticationService authenticationService;

    /**
     * The user currently connected to the application
     */
    private User authenticatedUser;

    @Inject
    public SessionManager(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Authenticates a user
     * @param username
     * @param password
     * @return
     */
    public void login(String username, String password) throws ValidationException {
        this.authenticatedUser = this.authenticationService.authenticate(username, password);
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return this.authenticatedUser;
    }

    public void logout() {
        this.authenticatedUser = null;
    }
}
