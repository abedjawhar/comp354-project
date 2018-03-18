package com.github.comp354project.model.auth;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AuthenticationModule {

    @Provides
    @Singleton
    public static IAuthenticationService provideAuthenticationService(AuthenticationService authenticationService){
        return authenticationService;
    }
}