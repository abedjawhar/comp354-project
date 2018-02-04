package com.github.comp354project.service.auth;

import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.service.user.User;

import javax.inject.Inject;

public class AuthenticationService implements IAuthenticationService {

    private IUserService userService;

    @Inject
    public AuthenticationService(IUserService userService){
        this.userService = userService;
    }

    @Override
    public User authenticate(String username, String password) {
        return null;
    }
}
