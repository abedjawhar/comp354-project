package com.github.comp354project.service.account;

import com.github.comp354project.service.account.exceptions.AccountDoesNotExistException;
import com.github.comp354project.service.account.exceptions.AccountExistsException;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;

public interface IAccountService {
    Account addAccount(GetRemoteAccountRequest request, User user) throws AccountExistsException, AccountDoesNotExistException, ValidationException;
    void deleteAccount(Account account) throws ValidationException;
}
