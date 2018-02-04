package com.github.comp354project.service.user.exceptions;

import com.github.comp354project.service.user.User;
import lombok.Builder;
import lombok.Getter;

public class UserExistsException extends RuntimeException {
    @Getter
    private User user;

    @Builder
    private UserExistsException(String message, Throwable cause, User user){
        super(message, cause);
        this.user = user;
    }
}
