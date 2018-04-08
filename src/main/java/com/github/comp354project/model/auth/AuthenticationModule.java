package com.github.comp354project.model.auth;

import com.github.comp354project.utils.ProxyFactory;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AuthenticationModule {

    @Provides
    @Singleton
    public static IAuthenticationService provideAuthenticationService(AuthenticationService authenticationService){
        return ProxyFactory.newInstance(authenticationService, IAuthenticationService.class);
    }
}