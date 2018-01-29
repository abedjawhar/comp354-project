package com.github.comp354project.service.account;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.account.remote.IRemoteAccountService;
import com.github.comp354project.service.dao.IAccountDao;
import com.github.comp354project.service.exceptions.InvalidParameterException;

import javax.inject.Inject;
import java.util.List;

public class AccountService implements IAccountService {

    private IAccountDao accountDao;
    private IRemoteAccountService remoteAccountService;

    @Inject
    public AccountService(IAccountDao accountDao, IRemoteAccountService remoteAccountService) {
        this.accountDao = accountDao;
        this.remoteAccountService = remoteAccountService;
    }

    @Override
    public List<Account> getAccounts(Integer userID) throws InvalidParameterException, DatabaseException {
        return null;
    }

    @Override
    public Account addAccount(GetRemoteAccountRequest request) throws InvalidParameterException, DatabaseException {
        return null;
    }
}
