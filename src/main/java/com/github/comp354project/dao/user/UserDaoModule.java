package com.github.comp354project.dao.user;

import com.github.comp354project.dao.user.impl.UserDao;
import dagger.Module;
import dagger.Provides;

/**
 * This module is used for everything related to a user (login/logout/create account etc)
 */
@Module
public class UserDaoModule {

    @Provides
    static IUserDao provideUserDao(UserDao userDao) {
        return userDao;
    }
}
