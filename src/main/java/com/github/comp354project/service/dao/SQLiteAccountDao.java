package com.github.comp354project.service.dao;

import com.github.comp354project.service.sqlite.ConnectionProvider;
import com.github.comp354project.service.sqlite.IConnectionProvider;

import javax.inject.Inject;
import java.sql.Connection;

public class SQLiteAccountDao implements IAccountDao {
    private IConnectionProvider connectionProvider;

    @Inject
    public SQLiteAccountDao(IConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }
}
