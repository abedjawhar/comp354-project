package com.github.comp354project.service.user;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.auth.exceptions.AuthenticationException;
import com.github.comp354project.service.auth.exceptions.AuthorisationException;
import com.github.comp354project.service.exceptions.ValidationException;

public interface IUserService {
    User createUser(User user) throws ValidationException;
    void deleteBankAccount(Account account) throws ValidationException, AuthenticationException, AuthorisationException;
}
