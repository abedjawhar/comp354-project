package com.github.comp354project.service.account.remote;

import javax.inject.Inject;

public class RemoteAccountService implements IRemoteAccountService {
    private RemoteAccountDao remoteAccountDao;

    @Inject
    public RemoteAccountService(RemoteAccountDao remoteAccountDao){
        this.remoteAccountDao = remoteAccountDao;
    }

    @Override
    public GetRemoteAccountResponse getAccount(GetRemoteAccountRequest request) throws IllegalArgumentException {
        return null;
    }
}
