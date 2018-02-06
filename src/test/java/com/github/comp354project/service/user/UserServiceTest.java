package com.github.comp354project.service.user;

import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.exceptions.UserExistsException;
import com.google.common.collect.ImmutableList;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sun.javafx.scene.control.TableColumnSortTypeWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    protected static final Logger logger = LogManager.getLogger(DatabaseException.class);

    private UserService userService;

    @Before
    public void setUp() throws Exception{
        JdbcConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        Dao<User, Integer> userDao = DaoManager.createDao(connectionSource, User.class);
        TableUtils.createTable(connectionSource, User.class);
        userService = new UserService(userDao);
    }

    @Test(expected = ValidationException.class)
    public void createUser_withNullUser_shouldThrow(){
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
    public void testCreateUser_withValidUser_shouldReturnUser(){
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

    @Test(expected = UserExistsException.class)
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

    @Test(expected = ValidationException.class)
    public void testGetUser_withNullUsername_shouldThrow(){
        userService.getUser(null);
    }

    @Test(expected = ValidationException.class)
    public void testGetUser_withEmptyUsername_shouldThrow(){
        userService.getUser("");
    }
}
