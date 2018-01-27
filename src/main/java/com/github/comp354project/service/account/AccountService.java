package com.github.comp354project.service.account;

import com.github.comp354project.service.DatabaseException;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.account.remote.IRemoteAccountService;

import javax.inject.Inject;
import java.util.List;

public class AccountService implements IAccountService {

    private AccountDao bankAccountDao;
    private IRemoteAccountService remoteAccountService;

    @Inject
    public AccountService(AccountDao bankAccountDao, IRemoteAccountService remoteAccountService) {
        this.bankAccountDao = bankAccountDao;
        this.remoteAccountService = remoteAccountService;
    }

    @Override
    public List<Account> getAccounts(Integer userID) throws IllegalArgumentException, DatabaseException {
        return null;
    }

    @Override
    public Account addAccount(GetRemoteAccountRequest request) throws IllegalArgumentException, DatabaseException {
        return null;
    }
}
