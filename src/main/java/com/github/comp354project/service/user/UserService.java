package com.github.comp354project.service.user;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.dao.IUserDao;
import com.github.comp354project.service.exceptions.InvalidParameterException;

import javax.inject.Inject;

public class UserService implements IUserService {

    private IUserDao userDao;

    @Inject
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(User user) throws InvalidParameterException, DatabaseException {
        return null;
    }

    @Override
    public User getUser(String username) throws InvalidParameterException, DatabaseException {
        return null;
    }
}
