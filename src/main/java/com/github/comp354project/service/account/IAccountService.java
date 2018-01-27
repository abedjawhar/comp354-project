package com.github.comp354project.service.account;

import com.github.comp354project.service.DatabaseException;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;

import java.util.List;

public interface IAccountService {
    List<Account> getAccounts(Integer userID) throws IllegalArgumentException, DatabaseException;
    Account addAccount(GetRemoteAccountRequest request) throws IllegalArgumentException, DatabaseException;
}
