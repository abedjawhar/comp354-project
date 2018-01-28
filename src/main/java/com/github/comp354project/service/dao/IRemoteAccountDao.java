package com.github.comp354project.service.dao;

import com.github.comp354project.service.DatabaseException;
import com.github.comp354project.service.account.remote.RemoteAccount;

public interface IRemoteAccountDao {
    RemoteAccount getRemoteAccount(String ID) throws IllegalArgumentException, DatabaseException;
}
