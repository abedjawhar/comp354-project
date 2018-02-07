package com.github.comp354project.service.sqlite;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ConnectionModule {

    @Provides
    @Singleton
    static IConnectionProvider provideConnection(ConnectionProvider connectionProvider) {
        return connectionProvider;
    }
}
