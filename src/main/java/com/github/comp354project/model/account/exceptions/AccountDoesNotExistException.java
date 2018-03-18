package com.github.comp354project.model.account.exceptions;

import com.github.comp354project.model.account.remote.GetRemoteAccountRequest;
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
