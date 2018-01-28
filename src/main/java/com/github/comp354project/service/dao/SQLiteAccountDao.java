package com.github.comp354project.service.dao;

import com.github.comp354project.service.sqlite.IConnectionService;

import javax.inject.Inject;

public class SQLiteAccountDao implements IAccountDao {

    @Inject
    IConnectionService connectionService;

    @Inject
    public SQLiteAccountDao() {}

    public Double getBalance() {
        return 12345.67;
    }
}
