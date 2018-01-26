package com.github.comp354project.service.user.impl;

import com.github.comp354project.dao.user.IUserDao;
import com.github.comp354project.service.user.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {

    private IUserDao userDao;

    @Inject
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }
}
