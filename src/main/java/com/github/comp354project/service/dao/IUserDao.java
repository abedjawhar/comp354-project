package com.github.comp354project.service.dao;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.user.User;

public interface IUserDao  {
    User createUser(User user) throws DatabaseException;
    User getUser(String username) throws DatabaseException;
}
