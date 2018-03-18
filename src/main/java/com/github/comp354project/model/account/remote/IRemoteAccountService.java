package com.github.comp354project.model.account.remote;

import com.github.comp354project.model.exceptions.ValidationException;

public interface IRemoteAccountService {
    GetRemoteAccountResponse getAccount(GetRemoteAccountRequest request) throws ValidationException;
}
