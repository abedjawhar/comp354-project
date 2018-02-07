package com.github.comp354project.service.auth;

import com.github.comp354project.service.auth.exceptions.UserLoggedInException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private IAuthenticationService authenticationService;

    @Getter
    private User user;

    @Inject
    public SessionManager(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * @param username
     * @param password
     * @return The logged in User
     * @throws ValidationException If the username and/or password is incorrect/invalid
     * @throws UserLoggedInException If a user is already logged in
     */
    public synchronized User login(String username, String password) throws ValidationException, UserLoggedInException {
        if(this.isLoggedIn()){
            throw UserLoggedInException.builder()
                    .message("Already logged in.")
                    .user(this.user).build();
        }
        this.user = this.authenticationService.authenticate(username, password);
        return this.user;
    }

    public synchronized void logout() {
        this.user = null;
    }

    public synchronized boolean isLoggedIn(){
        return user != null;
    }
}
