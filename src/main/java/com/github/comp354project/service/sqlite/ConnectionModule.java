package com.github.comp354project.service.sqlite;

import dagger.Module;
import dagger.Provides;

@Module
public class ConnectionModule {

    @Provides
    static IConnectionService provideConnectionService(ConnectionService connectionService) {
        return connectionService;
    }
}
