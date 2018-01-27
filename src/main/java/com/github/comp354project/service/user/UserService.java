package com.github.comp354project.service.user;

import com.github.comp354project.service.DatabaseException;

import javax.inject.Inject;

public class UserService implements IUserService {

    private UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
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
