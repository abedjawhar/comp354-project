package com.github.comp354project.service.user;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.InvalidParameterException;

public interface IUserService {
    User createUser(User user) throws InvalidParameterException, DatabaseException;
    User getUser(String username) throws InvalidParameterException, DatabaseException;
}
