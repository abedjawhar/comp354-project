package com.github.comp354project.service.auth;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthenticationModule {

    @Provides
    public static IAuthenticationService provideAuthenticationService(AuthenticationService authenticationService){
        return authenticationService;
    }
}