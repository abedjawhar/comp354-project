package com.github.comp354project.service.account.exceptions;

import com.github.comp354project.service.account.Account;
import lombok.Builder;
import lombok.Getter;

public class AccountExistsException extends RuntimeException {
    @Getter
    private Account account;

    @Builder
    public AccountExistsException(String message, Throwable cause, Account account){
        super(message, cause);
        this.account = account;
    }
}
