package com.github.comp354project.model.auth.exceptions;

import com.github.comp354project.model.user.User;
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
