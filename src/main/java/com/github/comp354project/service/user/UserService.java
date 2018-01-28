package com.github.comp354project.service.user;

import com.github.comp354project.service.DatabaseException;
import com.github.comp354project.service.dao.IUserDao;

import javax.inject.Inject;

public class UserService implements IUserService {

    private IUserDao userDao;

    @Inject
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(User user) throws IllegalArgumentException, DatabaseException {
        return null;
    }

    @Override
    public User getUser(String username) throws IllegalArgumentException, DatabaseException {
        return null;
    }
}
