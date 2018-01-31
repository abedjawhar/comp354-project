package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.dao.IRemoteAccountDao;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;

import javax.inject.Inject;

public class RemoteAccountService implements IRemoteAccountService {
    private IRemoteAccountDao remoteAccountDao;

    @Inject
    public RemoteAccountService(IRemoteAccountDao remoteAccountDao){
        this.remoteAccountDao = remoteAccountDao;
    }

    @Override
    public GetRemoteAccountResponse getAccount(GetRemoteAccountRequest request) throws ValidationException {
        if(request == null){
            throw ValidationException.builder()
                    .message("Null request")
                    .error(ValidationError.builder()
                            .parameterName("request")
                            .build()).build();
        }
        if(request.getAccountID() == null){
            throw ValidationException.builder()
                    .message("Invalid request")
                    .error(ValidationError.builder()
                            .parameterName("accountID")
                            .build()).build();
        }
        RemoteAccount account = remoteAccountDao.getRemoteAccount(request.getAccountID());
        return GetRemoteAccountResponse.builder().account(account).build();
    }
}
