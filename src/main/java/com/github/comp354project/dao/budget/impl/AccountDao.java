package com.github.comp354project.dao.budget.impl;

import com.github.comp354project.dao.budget.IAccountDao;

import javax.inject.Inject;

public class AccountDao implements IAccountDao {

    @Inject
    public AccountDao() {}

    @Override
    public Double getBalance() {
        return 12345.67;
    }
}
