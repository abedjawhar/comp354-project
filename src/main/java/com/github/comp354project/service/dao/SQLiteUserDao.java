package com.github.comp354project.service.dao;

import com.github.comp354project.service.sqlite.IConnectionProvider;

import javax.inject.Inject;
import java.sql.Connection;

public class SQLiteUserDao implements IUserDao{

    private IConnectionProvider connectionProvider;

    @Inject
    public SQLiteUserDao(IConnectionProvider connectionProvider){
        this.connectionProvider = connectionProvider;
    }
}