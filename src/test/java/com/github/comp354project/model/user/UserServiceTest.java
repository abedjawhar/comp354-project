package com.github.comp354project.model.user;

import com.github.comp354project.model.TestUtils;
import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.AccountService;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.account.remote.IRemoteAccountService;
import com.github.comp354project.model.auth.exceptions.AuthenticationException;
import com.github.comp354project.model.auth.exceptions.AuthorisationException;
import com.github.comp354project.model.auth.SessionManager;
import com.github.comp354project.model.auth.exceptions.UserLoggedInException;
import com.github.comp354project.model.exceptions.DatabaseException;
import com.github.comp354project.model.exceptions.ValidationException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    protected static final Logger logger = LogManager.getLogger(DatabaseException.class);

    private UserService userService;
    private SessionManager sessionManager;
    private AccountService accountService;
    private Dao<Account, Integer> accountDao;
    private Dao<User, Integer> userDao;
    private Dao<Transaction, Integer> transactionDao;
    private IRemoteAccountService remoteAccountService;

    @Before
    public void setUp() throws Exception{
        JdbcConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        remoteAccountService = mock(IRemoteAccountService.class);
        userDao = DaoManager.createDao(connectionSource, User.class);
        sessionManager = mock(SessionManager.class);
        accountService = mock(AccountService.class);
        accountDao = DaoManager.createDao(connectionSource, Account.class);
        transactionDao = DaoManager.createDao(connectionSource, Transaction.class);
        userService = new UserService(userDao, accountDao, sessionManager, accountService);
        TableUtils.createTable(connectionSource, User.class);
        TableUtils.createTable(connectionSource, Account.class);
        TableUtils.createTable(connectionSource, Transaction.class);

    }

    @Test(expected = ValidationException.class)
    public void createUser_withNullUser_shouldThrow() throws Exception{
        userService.createUser(null);
    }

    @Test
    public void testCreateUser_withInvalidUser_shouldThrow(){
        boolean didThrow = false;
        try{
            userService.createUser(User.builder().build());
        } catch (ValidationException e){
            didThrow = true;
            // Must contain validation error for 4 member fields: username, password, firstname, lastname
            assertEquals(4,  e.getErrors().size());
        }
        assertTrue(didThrow);
    }

    @Test
    public void testCreateUser_withValidUser_shouldReturnUser() throws Exception{
        User expectedUser = User.builder()
                .username("USERNAME")
                .password("PASSWORD")
                .firstName("FIRSTNAME")
                .lastName("LASTNAME").build();

        User actualUser = userService.createUser(expectedUser);

        assertNotNull(actualUser.getID());
        expectedUser.setID(actualUser.getID());
        assertEquals(expectedUser, actualUser);
    }

    @Test(expected = ValidationException.class)
    public void testCreateUser_withExistingUsername_shouldThrow() throws Exception{
        User user = User.builder()
                .username("USERNAME")
                .password("PASSWORD")
                .firstName("FIRSTNAME")
                .lastName("LASTNAME").build();

        userService.createUser(user);
        assertNotNull(user.getID());
        user.setID(null);

        userService.createUser(user);
    }

    @Test(expected =  ValidationException.class)
    public void testDeleteBankAccount_withNullAccount_ShouldThrow() throws Exception{
        userService.deleteBankAccount(null);
    }

    @Test(expected = AuthenticationException.class)
    public void testDeleteBankAccount_withoutBeingLoggedIn_ShouldThrow() throws Exception{
        Account account = TestUtils.testAccount;
        accountDao.create(account);
        userService.deleteBankAccount(account);
    }

    @Test(expected = AuthorisationException.class)
    public void testDeleteBankAccount_withoutProperAuthorisation_ShouldThrow() throws Exception{
        User user = TestUtils.testUser;
        userDao.create(user);
        User user2 = User.builder()
               .username("username")
               .password("password")
               .firstName("firstname")
               .lastName("lastname")
               .ID(999)
               .build();
       userDao.create(user2);
       Account account = TestUtils.testAccount; // owned by user
       accountDao.create(account);
       when(sessionManager.isLoggedIn()).thenReturn(true);
       when(sessionManager.getUser()).thenReturn(user2);

       userService.deleteBankAccount(account);
    }

    @Test
    public void testDeleteBankAccount_WithProperAuthorisation_ShouldSucceed() throws Exception{
        User user = TestUtils.testUser;
        userDao.create(user);
        Account account = TestUtils.testAccount;
        accountDao.create(account);
        sessionManager.login(user.getUsername(), user.getPassword());
        when(sessionManager.isLoggedIn()).thenReturn(true);
        when(sessionManager.getUser()).thenReturn(user);
        userService.deleteBankAccount(account);
        verify(accountService, times(1)).deleteAccount(account);
    }

    @Test (expected = ValidationException.class)
    public void testDeleteUser_withNullUser_shouldThrow() throws ValidationException {
        userService.deleteUser(null);
    }

    @Test (expected = ValidationException.class)
    public void testDeleteUser_withNonexistantUser_shouldThrow() throws  ValidationException {

        userService.deleteUser(TestUtils.testUser);
    }
    @Test
    public void testDeleteUser_withExistingtUser_shouldSucceed() throws SQLException, ValidationException, UserLoggedInException {
        User user = TestUtils.testUser;
        userDao.create(user);
        sessionManager.login(user.getUsername(), user.getPassword());
        when(sessionManager.isLoggedIn()).thenReturn(true);
        when(sessionManager.getUser()).thenReturn(user);
        userService.deleteUser(user);

        assertEquals(0, userDao.queryForEq("id", user.getID()).size());
    }

    @Test
    public void testDeleteUser_withExistingtUser_shouldDeleteAssociatedAccounts() throws SQLException, ValidationException, UserLoggedInException {
        User user = TestUtils.testUser;
        userDao.create(user);
        Account account = TestUtils.testAccount;
        accountDao.create(account);
        sessionManager.login(user.getUsername(), user.getPassword());
        when(sessionManager.isLoggedIn()).thenReturn(true);
        when(sessionManager.getUser()).thenReturn(user);
        userService.deleteUser(user);
        assertEquals(0, userDao.queryForEq("id", user.getID()).size());
        verify(accountService, times(1)).deleteAccount(account);

    }



    @Test (expected = ValidationException.class)
    public void testUpdateUser_withNullUser_shouldThrow() throws ValidationException {
        userService.updateUser(null);
    }

    @Test (expected = ValidationException.class)
    public void testUpdateUser_withNonexistenttUser_shouldThrow() throws ValidationException {
        userService.updateUser(TestUtils.testUser);
    }


    @Test
    public void testUpdateUser_withValidUser_shouldSucceed() throws SQLException, ValidationException, UserLoggedInException {
        User user = TestUtils.testUser;
        userDao.create(user);
        sessionManager.login(user.getUsername(), user.getPassword());
        when(sessionManager.isLoggedIn()).thenReturn(true);
        when(sessionManager.getUser()).thenReturn(user);
        user.setFirstName("Abed");
        user.setLastName("jawhar");
        user.setPassword("admin2");
        userService.updateUser(user);
        assertEquals(true,userDao.queryForId(1).getFirstName().equals(user.getFirstName()));
        assertEquals(true,userDao.queryForId(1).getLastName().equals(user.getLastName()));
        assertEquals(true,userDao.queryForId(1).getPassword().equals(user.getPassword()));

    }

}
