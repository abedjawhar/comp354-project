package com.github.comp354project.model.account;

import com.github.comp354project.model.account.exceptions.AccountDoesNotExistException;
import com.github.comp354project.model.account.exceptions.AccountExistsException;
import com.github.comp354project.model.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;

import java.util.Collection;

public interface IAccountService {
    Account addAccount(GetRemoteAccountRequest request, User user) throws AccountExistsException, AccountDoesNotExistException, ValidationException;
    void deleteAccountsForUser(Integer userID) throws ValidationException;
    void deleteAccount(Account account) throws ValidationException;
}
