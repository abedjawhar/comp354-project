package com.github.comp354project.service.user;

import com.github.comp354project.service.exceptions.InvalidParameterException;

public interface IUserService {
    void createUser(User user) throws InvalidParameterException;
    User getUser(String username) throws InvalidParameterException;
}
