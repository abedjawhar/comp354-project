package com.github.comp354project.model.auth;

import com.github.comp354project.model.auth.exceptions.UserLoggedInException;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private IAuthenticationService authenticationService;

    @Getter
    private User user;

    public void setUser(User user){
        if(user == null)
            return;
        if(isLoggedIn() && this.user.getID().equals(user.getID())){
            this.user = user;
        }
    }

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
