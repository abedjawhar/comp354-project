package com.github.comp354project.model.account.remote;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteAccountModule {
    @Provides
    static IRemoteAccountService provideRemoteAccountService(RemoteAccountService remoteAccountService){
        return remoteAccountService;
    }
}