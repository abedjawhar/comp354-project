package com.github.comp354project.service.auth;

import com.github.comp354project.service.auth.exceptions.UserLoggedInException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;

public interface ISessionManager {
    User login(String username, String password) throws ValidationException, UserLoggedInException;

    void logout();

    boolean isLoggedIn();

    User getUser();
}
