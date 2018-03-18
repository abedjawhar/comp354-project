package com.github.comp354project.model.auth;

import com.github.comp354project.model.TestUtils;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import com.google.common.collect.ImmutableList;
import com.j256.ormlite.dao.Dao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    private Dao<User, Integer> userDao;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testAuthenticate_withInvalidUsernameOrPassword_shouldThrow(){
        boolean didThrow = false;
        try{
            authenticationService.authenticate(null, null);
        } catch(ValidationException e){
            assertEquals(2, e.getErrors().size());
            didThrow = true;
        }
        assertTrue(didThrow);
    }

    @Test(expected = ValidationException.class)
    public void testAuthenticate_withNonexistentUsername_shouldThrow() throws Exception{
        String username = "username";
        when(userDao.queryForEq(eq("username"),eq(username))).thenReturn(new ArrayList<>());

        authenticationService.authenticate(username, "password");
    }

    @Test(expected = ValidationException.class)
    public void testAuthenticate_withIncorrectPassword_shouldThrow() throws Exception{
        User user = TestUtils.testUser;
        String incorrectPassword = "INCORRECT_PASSOWORD";
        assertNotEquals(user.getPassword(), incorrectPassword);
        when(userDao.queryForEq(eq("username"),eq(user.getUsername()))).thenReturn(ImmutableList.<User>builder().add(user).build());

        authenticationService.authenticate(user.getUsername(), incorrectPassword);
    }

    @Test
    public void testAuthenticate_withCorrectCredentials_shouldReturnUser() throws Exception{
        User user = TestUtils.testUser;
        when(userDao.queryForEq(eq("username"),eq(user.getUsername()))).thenReturn(ImmutableList.<User>builder().add(user).build());

        User authenticatedUser = authenticationService.authenticate(user.getUsername(), user.getPassword());
        assertEquals(user, authenticatedUser);
    }
}
