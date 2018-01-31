package com.github.comp354project.service.account;

import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.exceptions.ValidationException;

import java.util.List;

public interface IAccountService {
    List<Account> getAccounts(Integer userID) throws ValidationException;
    Account addAccount(GetRemoteAccountRequest request) throws ValidationException;
}
