package com.github.comp354project.service.auth;

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

    @Provides
    @Singleton
    public static ISessionManager provideSessionManager(SessionManager sessionManager){
        return sessionManager;
    }
}