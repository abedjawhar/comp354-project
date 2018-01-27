package com.github.comp354project.service.user;


import javax.inject.Inject;

public class UserService implements IUserService {

    private UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
