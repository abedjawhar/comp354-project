package com.github.comp354project.service.user;

import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.AccountService;
import com.github.comp354project.service.auth.exceptions.AuthenticationException;
import com.github.comp354project.service.auth.exceptions.AuthorisationException;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    protected static final Logger logger = LogManager.getLogger(DatabaseException.class);

    private UserService userService;
    private SessionManager sessionManager;
    private AccountService accountService;
    private Dao<Account, Integer> accountDao;
    private Dao<User, Integer> userDao;

    @Before
    public void setUp() throws Exception{
        JdbcConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        userDao = DaoManager.createDao(connectionSource, User.class);
        TableUtils.createTable(connectionSource, User.class);
        sessionManager = mock(SessionManager.class);
        accountService = mock(AccountService.class);
        userService = new UserService(userDao, sessionManager, accountService);
        connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        accountDao = DaoManager.createDao(connectionSource, Account.class);
        TableUtils.createTable(connectionSource, User.class);
        TableUtils.createTable(connectionSource, Account.class);
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
}
