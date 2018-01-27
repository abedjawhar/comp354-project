package com.github.comp354project.service.account;

import com.github.comp354project.service.sqlite.IConnectionService;

import javax.inject.Inject;

public class AccountDao  {

    @Inject
    IConnectionService connectionService;

    @Inject
    public AccountDao() {}

    public Double getBalance() {
        return 12345.67;
    }
}
