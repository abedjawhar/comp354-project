package com.github.comp354project.dao.budget.impl;

import com.github.comp354project.dao.budget.IBankAccountDao;

public class BankAccountDao implements IBankAccountDao {

    @Override
    public Double getBalance() {
        return 12345.67;
    }
}
