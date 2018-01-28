package com.github.comp354project.service.dao;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {
    @Provides
    static IRemoteAccountDao provideRemoteAccountDao(SQLiteRemoteAccountDao remoteAccountDao) {
        return remoteAccountDao;
    }

    @Provides
    static IAccountDao provideAccountDao(SQLiteAccountDao accountDao) {
        return accountDao;
    }

    @Provides
    static IUserDao provideUserDao(SQLiteUserDao sqLiteUserDao) {
        return sqLiteUserDao;
    }
}
