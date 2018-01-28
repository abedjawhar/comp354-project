package com.github.comp354project.service.dao;

import com.github.comp354project.service.DatabaseException;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.sqlite.IConnectionService;

import javax.inject.Inject;

public class SQLiteRemoteAccountDao implements IRemoteAccountDao {

    private IConnectionService connectionService;

    @Inject
    public SQLiteRemoteAccountDao(IConnectionService connectionService){
        this.connectionService = connectionService;
    }

    @Override
    public RemoteAccount getRemoteAccount(String ID) throws IllegalArgumentException, DatabaseException {
        return null;
    }
}
