package com.github.comp354project.model.user;

import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.auth.exceptions.AuthenticationException;
import com.github.comp354project.model.auth.exceptions.AuthorisationException;
import com.github.comp354project.model.exceptions.ValidationException;

import java.sql.SQLException;

public interface IUserService {
    User createUser(User user) throws ValidationException;
    void deleteBankAccount(Account account) throws ValidationException, AuthenticationException, AuthorisationException;
    User updateUser(User user) throws ValidationException, AuthorisationException, AuthenticationException;
    void deleteUser(User user) throws ValidationException, AuthorisationException, AuthenticationException;
}
