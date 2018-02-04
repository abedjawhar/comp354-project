package com.github.comp354project.service.account.exceptions;

import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import lombok.Builder;
import lombok.Getter;

public class AccountDoesNotExistException extends RuntimeException {
    @Getter
    private GetRemoteAccountRequest request;

    @Builder
    public AccountDoesNotExistException(String message, Throwable cause, GetRemoteAccountRequest request){
        super(message, cause);
        this.request = request;
    }
}
