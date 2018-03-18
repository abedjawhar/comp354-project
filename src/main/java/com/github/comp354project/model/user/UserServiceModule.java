package com.github.comp354project.model.user;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * This module is used for classes linked to the user (creation of user/login etc)
 */
@Module
public class UserServiceModule {

    @Provides
    @Singleton
    static IUserService provideUserService(UserService userService) {
        return userService;
    }
}
