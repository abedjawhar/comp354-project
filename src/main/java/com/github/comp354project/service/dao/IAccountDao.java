package com.github.comp354project.service.dao;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.exceptions.DatabaseException;

import java.util.List;

public interface IAccountDao {
    List<Account> getAccounts(Integer userID) throws DatabaseException;
    Account createAccount(Account account) throws DatabaseException;
}
