package com.github.comp354project.service.account;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.exceptions.InvalidParameterException;

import java.util.List;

public interface IAccountService {
    List<Account> getAccounts(Integer userID) throws InvalidParameterException;
    Account addAccount(GetRemoteAccountRequest request) throws InvalidParameterException;
}
