package com.github.comp354project.service.auth.exceptions;

import com.github.comp354project.service.user.User;
import lombok.Builder;
import lombok.Getter;

public class UserLoggedInException extends Exception {
    @Getter
    private User user;

    @Builder
    public UserLoggedInException(String message, Throwable cause, User user){
        super(message, cause);
        this.user = user;
    }
}
