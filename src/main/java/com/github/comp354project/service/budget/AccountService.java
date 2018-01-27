package com.github.comp354project.service.budget;

import javax.inject.Inject;

public class AccountService implements IAccountService {

    private AccountDao bankAccountDao;

    @Inject
    public AccountService(AccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public Double getBalance() {
        return this.bankAccountDao.getBalance();
    }
}
