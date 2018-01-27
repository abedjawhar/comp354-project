package com.github.comp354project.service.user;

import com.github.comp354project.service.DatabaseException;

public interface IUserService {
    User createUser(User user) throws IllegalArgumentException, DatabaseException;
    User getUser(String username) throws IllegalArgumentException, DatabaseException;
}
