package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.sqlite.IConnectionService;

import javax.inject.Inject;

public class RemoteAccountDao {

    private IConnectionService connectionService;

    @Inject
    public RemoteAccountDao(IConnectionService connectionService){
        this.connectionService = connectionService;
    }
}
