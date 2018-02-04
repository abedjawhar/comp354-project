package com.github.comp354project.service.auth;

import com.github.comp354project.service.user.User;

public interface IAuthenticationService {
    User authenticate(String username, String password);
}
