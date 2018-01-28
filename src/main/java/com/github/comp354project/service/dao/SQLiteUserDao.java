package com.github.comp354project.service.dao;

import com.github.comp354project.service.sqlite.IConnectionService;

import javax.inject.Inject;

public class SQLiteUserDao implements IUserDao{

    private IConnectionService connectionService;

    @Inject
    public SQLiteUserDao(IConnectionService connectionService){
        this.connectionService = connectionService;
    }
}