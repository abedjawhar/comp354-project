package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.exceptions.ValidationException;

public interface IRemoteAccountService {
    GetRemoteAccountResponse getAccount(GetRemoteAccountRequest request) throws ValidationException;
}
