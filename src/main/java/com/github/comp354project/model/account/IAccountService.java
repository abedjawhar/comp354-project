package com.github.comp354project.model.account;

import com.github.comp354project.model.account.exceptions.AccountDoesNotExistException;
import com.github.comp354project.model.account.exceptions.AccountExistsException;
import com.github.comp354project.model.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import com.github.comp354project.utils.Timing;

import java.util.Collection;

public interface IAccountService {
    @Timing
    Account addAccount(GetRemoteAccountRequest request, User user) throws AccountExistsException, AccountDoesNotExistException, ValidationException;
    @Timing
    void deleteAccountsForUser(Integer userID) throws ValidationException;
    @Timing
    void deleteAccount(Account account) throws ValidationException;
}
