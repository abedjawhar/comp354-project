package com.github.comp354project.service.user;

import com.github.comp354project.service.user.impl.UserService;
import dagger.Module;
import dagger.Provides;

/**
 * This module is used for classes linked to the user (creation of user/login etc)
 */
@Module
public class UserServiceModule {
    @Provides
    static IUserService provideUserService(UserService userService) {
        return userService;
    }
}
