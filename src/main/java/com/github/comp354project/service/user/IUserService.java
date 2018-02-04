package com.github.comp354project.service.user;

import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.exceptions.UserExistsException;

public interface IUserService {
    User createUser(User user) throws UserExistsException, ValidationException;
    User getUser(String username) throws ValidationException;
}
