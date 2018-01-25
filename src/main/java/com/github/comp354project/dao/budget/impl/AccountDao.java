package com.github.comp354project.dao.budget.impl;

import com.github.comp354project.dao.budget.IAccountDao;

public class AccountDao implements IAccountDao {

    @Override
    public Double getBalance() {
        return 12345.67;
    }
}
