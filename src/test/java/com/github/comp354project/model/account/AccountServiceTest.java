package com.github.comp354project.model.account;


import com.github.comp354project.model.TestUtils;
import com.github.comp354project.model.account.exceptions.AccountDoesNotExistException;
import com.github.comp354project.model.account.exceptions.AccountExistsException;
import com.github.comp354project.model.account.remote.*;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import com.github.comp354project.model.user.UserService;
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

import java.sql.SQLException;
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
    private Dao<Transaction, Integer> transactionDao;
    private JdbcConnectionSource connectionSource;

    @Before
    public void setUp() throws Exception{
        connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        userDao = DaoManager.createDao(connectionSource, User.class);
        accountDao = DaoManager.createDao(connectionSource, Account.class);
        transactionDao = DaoManager.createDao(connectionSource, Transaction.class);
        remoteAccountService = mock(IRemoteAccountService.class);
        accountService = new AccountService(accountDao, userDao, transactionDao, remoteAccountService);
        TableUtils.createTable(connectionSource, User.class);
        TableUtils.createTable(connectionSource, Account.class);
        TableUtils.createTable(connectionSource, Transaction.class);
        TableUtils.createTable(connectionSource, RemoteTransaction.class);
        TableUtils.createTable(connectionSource, RemoteAccount.class);
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
    public void testAddAccount_withNonexistentRemoteAccount_shouldThrow() throws Exception{
        User accountOwner = TestUtils.testUser;
        userDao.create(accountOwner);
        GetRemoteAccountResponse sampleResponse = GetRemoteAccountResponse.builder().build();
        GetRemoteAccountRequest sampleRequest = GetRemoteAccountRequest.builder()
                .accountID(TestUtils.testRemoteAccount.getID()).build();

        when(remoteAccountService.getAccount(any(GetRemoteAccountRequest.class)))
                .thenReturn(sampleResponse);

        accountService.addAccount(sampleRequest, TestUtils.testUser);
    }

    @Test(expected = ValidationException.class)
    public void testAddAccount_withInvalidUser_shouldThrow() throws ValidationException{
        User user = TestUtils.testUser;
        GetRemoteAccountRequest sampleRequest = GetRemoteAccountRequest.builder()
                .accountID(TestUtils.testRemoteAccount.getID()).build();

        accountService.addAccount(sampleRequest, user);
    }

    @Test(expected = AccountExistsException.class)
    public void testAddAccount_withExistingAccount_shouldThrow() throws Exception{
        User accountOwner = TestUtils.testUser;
        userDao.create(accountOwner);
        Account existingAccount = TestUtils.testAccount;
        existingAccount.setUser(accountOwner);
        accountDao.create(existingAccount);
        RemoteAccount remoteAccount = TestUtils.createTestRemoteAccount(connectionSource);

        GetRemoteAccountRequest request = GetRemoteAccountRequest.builder()
                .accountID(remoteAccount.getID()).build();
        GetRemoteAccountResponse response = GetRemoteAccountResponse.builder()
                .account(remoteAccount).build();
        when(remoteAccountService.getAccount(eq(request))).thenReturn(response);

        accountService.addAccount(request, accountOwner);
    }

    @Test
    public void testAddAccount_withValidAccount_shouldReturnValidAccount() throws Exception{
        User accountOwner = TestUtils.testUser;
        userDao.create(accountOwner);

        Account expectedAccount = TestUtils.testAccount;

        RemoteAccount remoteAccount = TestUtils.createTestRemoteAccount(connectionSource);

        GetRemoteAccountRequest request = GetRemoteAccountRequest.builder()
                .accountID(TestUtils.testRemoteAccount.getID()).build();
        GetRemoteAccountResponse response = GetRemoteAccountResponse.builder()
                .account(remoteAccount).build();
        when(remoteAccountService.getAccount(eq(request))).thenReturn(response);

        Account actualAccount = accountService.addAccount(request, accountOwner);

        assertEquals(expectedAccount, actualAccount);
        assertEquals(1, actualAccount.getTransactions().size());
    }

    // deleteAccount() tests
    @Test(expected = ValidationException.class)
    public void testDeleteAccount_withNullAccount_shouldThrow() throws ValidationException {
        accountService.deleteAccount(null);
    }

    @Test(expected = ValidationException.class)
    public void testDeleteAccount_withAccountWithNullID_shouldThrow() throws ValidationException{
        accountService.deleteAccount(Account.builder().build());
    }

   @Test(expected = ValidationException.class)
    public void testDeleteAccount_withNonExistentAccount_shouldThrow() throws ValidationException {
       accountService.deleteAccount(TestUtils.testAccount);
    }

   @Test
    public void testDeleteAccount_withValidAccount_shouldSucceed() throws Exception{
        User accountOwner = TestUtils.testUser;
        userDao.create(accountOwner);
        Account existingAccount = TestUtils.testAccount;
        accountDao.create(existingAccount);

        accountService.deleteAccount(existingAccount);
        Account actualAccount = accountDao.queryForId(existingAccount.getID());

        assertNull(actualAccount);
    }

   @Test
    public void testDeleteAccount_withValidAccount_shouldDeleteAllAssociatedTransactionsAndAccount() throws Exception{
        User accountOwner = TestUtils.testUser;
        userDao.create(accountOwner);
        Account existingAccount = TestUtils.testAccount;
        accountDao.create(existingAccount);
        transactionDao.create(TestUtils.testTransaction);

        accountService.deleteAccount(existingAccount);
        List<Transaction> leftoverTransactions = transactionDao.queryForEq("account_id", existingAccount);
        Account actualAccount = accountDao.queryForId(existingAccount.getID());

        assertTrue(leftoverTransactions.isEmpty());
    }
}