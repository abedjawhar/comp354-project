package com.github.comp354project.service.account.exceptions;

import com.github.comp354project.service.user.User;
import lombok.Getter;
import lombok.Builder;

public class AuthorisationException extends RuntimeException {

    @Getter
    private User user;

    @Builder
    public AuthorisationException(String message, Throwable cause, User user) {
        super(message, cause);
        this.user = user;
    }
}
