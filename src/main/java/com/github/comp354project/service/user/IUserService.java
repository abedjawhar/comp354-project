package com.github.comp354project.service.user;

import com.github.comp354project.service.exceptions.ValidationException;

public interface IUserService {
    User createUser(User user) throws ValidationException;
    User getUser(String username) throws ValidationException;
}
