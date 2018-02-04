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

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    protected static final Logger logger = LogManager.getLogger(DatabaseException.class);

    @Mock
    private Dao<User, Integer> userDao;

    @InjectMocks
    private UserService userService;

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
        User expectedUser = TestUtils.testUser;

        User actualUser = userService.createUser(expectedUser);

        assertEquals(expectedUser, actualUser);
    }

    @Test(expected = UserExistsException.class)
    public void testCreateUser_withExistingUser_shouldThrow() throws Exception{
        User existingUser = TestUtils.testUser;
        when(userDao.queryForEq(eq("username"), eq(existingUser.getUsername())))
                .thenReturn(ImmutableList.<User>builder()
                     .add(existingUser).build());

        User userToCreate = TestUtils.testUser;
        userService.createUser(userToCreate);
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
