package com.github.comp354project.service.auth;

import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.IUserService;
import com.github.comp354project.service.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    private IUserService userService;

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
    public void testAuthenticate_withNonexistentUsername_shouldThrow(){
        String username = "username";
        when(userService.getUser(eq(username))).thenReturn(null);

        authenticationService.authenticate(username, "password");
    }

    @Test(expected = ValidationException.class)
    public void testAuthenticate_withIncorrectPassword_shouldThrow(){
        User user = TestUtils.testUser;
        String incorrectPassword = "INCORRECT_PASSOWORD";
        assertNotEquals(user.getPassword(), incorrectPassword);
        when(userService.getUser(eq(user.getUsername()))).thenReturn(user);

        authenticationService.authenticate(user.getUsername(), incorrectPassword);
    }

    @Test
    public void testAuthenticate_withCorrectCredentials_shouldReturnUser(){
        User user = TestUtils.testUser;
        when(userService.getUser(eq(user.getUsername()))).thenReturn(user);

        User authenticatedUser = authenticationService.authenticate(user.getUsername(), user.getPassword());
        assertEquals(user, authenticatedUser);
    }
}
