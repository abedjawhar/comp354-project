package com.github.comp354project.service.user;

import com.github.comp354project.service.dao.IUserDao;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    protected static final Logger logger = LogManager.getLogger(DatabaseException.class);

    @Mock
    IUserDao userDao;

    @InjectMocks
    UserService userService;

    @Test(expected = ValidationException.class)
    public void createUser_withNullUser_shouldThrow(){
        userService.createUser(null);
    }

    @Test
    public void createUser_withInvalidUser_shouldThrow(){
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
    public void createUser_withValidUser_shouldReturnUser(){
        User user = User.builder()
                .firstName("FIRST")
                .lastName("LAST")
                .password("PASSWORD")
                .username("USERNAME").build();
        when(userDao.createUser(any(User.class))).thenReturn(user);

        assertNotNull(userService.createUser(user));
    }

    @Test(expected = ValidationException.class)
    public void getUser_withNullUsername_shouldThrow(){
        userService.getUser(null);
    }

    @Test(expected = ValidationException.class)
    public void getUser_withEmptyUsername_shouldThrow(){
        userService.getUser("");
    }

    @Test
    public void getUser_withNonexistentUsername_shouldReturnNull(){
        String nonexistentUsername = "NONEXISTENT_USERNAME";
        when(userDao.getUser(eq(nonexistentUsername))).thenReturn(null);

        assertNull(userService.getUser(nonexistentUsername));
    }

    @Test
    public void getUser_withValidUsername_shouldReturnUser(){
        String validUsername = "USERNAME";
        when(userDao.getUser(eq(validUsername))).thenReturn(User.builder().build());

        assertNotNull(userService.getUser(validUsername));
    }
}
