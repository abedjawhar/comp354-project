package com.github.comp354project.model.user;

import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.auth.exceptions.AuthenticationException;
import com.github.comp354project.model.auth.exceptions.AuthorisationException;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.utils.Timing;

import java.sql.SQLException;

public interface IUserService {
    @Timing
    User createUser(User user) throws ValidationException;
    @Timing
    void deleteBankAccount(Account account) throws ValidationException, AuthenticationException, AuthorisationException;
    @Timing
    User updateUser(User user) throws ValidationException, AuthorisationException, AuthenticationException;
    @Timing
    void deleteUser(User user) throws ValidationException, AuthorisationException, AuthenticationException;
}
