package com.github.comp354project.service.account.remote;

public interface IRemoteAccountService {
    GetRemoteAccountResponse getAccount(GetRemoteAccountRequest request) throws IllegalArgumentException;
}
