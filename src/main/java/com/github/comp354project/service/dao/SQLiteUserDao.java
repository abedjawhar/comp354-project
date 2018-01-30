package com.github.comp354project.service.dao;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.sqlite.IConnectionProvider;
import com.github.comp354project.service.user.User;

import javax.inject.Inject;
import java.sql.Connection;

public class SQLiteUserDao implements IUserDao{

    private IConnectionProvider connectionProvider;

    @Inject
    public SQLiteUserDao(IConnectionProvider connectionProvider){
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void createUser(User user) throws DatabaseException {

    }

    @Override
    public User getUser(String username) throws DatabaseException {
        return null;
    }
}