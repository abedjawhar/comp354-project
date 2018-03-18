package com.github.comp354project.model.auth.exceptions;

import com.github.comp354project.model.user.User;
import lombok.Getter;
import lombok.Builder;

public class AuthorisationException extends Exception {

    @Getter
    private User user;

    @Builder
    public AuthorisationException(String message, Throwable cause, User user) {
        super(message, cause);
        this.user = user;
    }
}
