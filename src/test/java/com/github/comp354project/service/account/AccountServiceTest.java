package com.github.comp354project.service.account;


import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.account.exceptions.AccountDoesNotExistException;
import com.github.comp354project.service.account.exceptions.AccountExistsException;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.account.remote.GetRemoteAccountResponse;
import com.github.comp354project.service.account.remote.IRemoteAccountService;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;
import com.github.comp354project.service.user.UserService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest{

    private IRemoteAccountService remoteAccountService;
    private AccountService accountService;
    private Dao<User, Integer> userDao;
    private Dao<Account, Integer> accountDao;

    @Before
    public void setUp() throws Exception{
        JdbcConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        userDao = DaoManager.createDao(connectionSource, User.class);
        accountDao = DaoManager.createDao(connectionSource, Account.class);
        Dao<Transaction, Integer> transactionDao = DaoManager.createDao(connectionSource, Transaction.class);
        remoteAccountService = mock(IRemoteAccountService.class);
        accountService = new AccountService(accountDao, userDao, transactionDao, remoteAccountService);
        TableUtils.createTable(connectionSource, User.class);
        TableUtils.createTable(connectionSource, Account.class);
        TableUtils.createTable(connectionSource, Transaction.class);
    }

    @Test
    public void testAddAccount_withInvalidParameters_shouldThrow(){
        boolean didThrow = false;
        try{
            accountService.addAccount(null, null);
        } catch(ValidationException e){
            didThrow = true;
            assertEquals(2, e.getErrors().size());
        }
        assertTrue(didThrow);
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void testAddAccount_withNonexistentRemoteAccount_shouldThrow(){
        GetRemoteAccountResponse sampleResponse = GetRemoteAccountResponse.builder().build();
        GetRemoteAccountRequest sampleRequest = GetRemoteAccountRequest.builder()
                .accountID(TestUtils.testRemoteAccount.getID()).build();

        when(remoteAccountService.getAccount(any(GetRemoteAccountRequest.class)))
                .thenReturn(sampleResponse);

        accountService.addAccount(sampleRequest, TestUtils.testUser);
    }

    @Test(expected = AccountExistsException.class)
    public void testAddAccount_withExistingAccount_shouldThrow() throws Exception{
        User accountOwner = TestUtils.testUser;
        userDao.create(accountOwner);
        Account existingAccount = TestUtils.testAccount;
        existingAccount.setUser(accountOwner);
        accountDao.create(existingAccount);
        RemoteAccount remoteAccount = TestUtils.testRemoteAccount;
        GetRemoteAccountRequest request = GetRemoteAccountRequest.builder()
                .accountID(remoteAccount.getID()).build();
        GetRemoteAccountResponse response = GetRemoteAccountResponse.builder()
                .account(remoteAccount).build();
        when(remoteAccountService.getAccount(eq(request))).thenReturn(response);

        accountService.addAccount(request, accountOwner);
    }
}