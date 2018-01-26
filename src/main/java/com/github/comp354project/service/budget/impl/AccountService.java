package com.github.comp354project.service.budget.impl;

import com.github.comp354project.dao.budget.IAccountDao;
import com.github.comp354project.service.budget.IAccountService;

import javax.inject.Inject;

public class AccountService implements IAccountService {

    private IAccountDao bankAccountDao;

    // Add inject to the constructor in your service/dao class to instantiate the sub classes
    @Inject
    public AccountService(IAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public Double getBalance() {
        return this.bankAccountDao.getBalance();
    }
}
