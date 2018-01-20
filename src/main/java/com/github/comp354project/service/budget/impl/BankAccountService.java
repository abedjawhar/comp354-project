package com.github.comp354project.service.budget.impl;

import com.github.comp354project.dao.budget.IBankAccountDao;
import com.github.comp354project.service.budget.IBankAccountService;

import javax.inject.Inject;

public class BankAccountService implements IBankAccountService {

    private IBankAccountDao bankAccountDao;

    @Inject
    public BankAccountService(IBankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public Double getBalance() {
        return this.bankAccountDao.getBalance();
    }
}
