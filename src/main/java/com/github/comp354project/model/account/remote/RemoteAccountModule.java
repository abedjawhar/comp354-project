package com.github.comp354project.model.account.remote;

import com.github.comp354project.utils.ProxyFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class RemoteAccountModule {
    @Provides
    static IRemoteAccountService provideRemoteAccountService(RemoteAccountService remoteAccountService){
        return ProxyFactory.newInstance(remoteAccountService, IRemoteAccountService.class);
    }
}