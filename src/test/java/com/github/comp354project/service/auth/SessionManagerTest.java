package com.github.comp354project.service.auth;

import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.auth.exceptions.UserLoggedInException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionManagerTest {

    @Mock
    private IAuthenticationService authenticationService;

    @InjectMocks
    private SessionManager sessionManager;

    @Test(expected = ValidationException.class)
    public void testLogin_withInvalidCredentials_shouldThrow() throws Exception{
        when(authenticationService.authenticate(any(String.class), any(String.class))).thenThrow(ValidationException.builder().build());

        sessionManager.login("", "");
        verify(authenticationService, times(1)).authenticate(any(String.class), any(String.class));
    }

    @Test
    public void testLogin_withValidCredentials_shouldReturnUser() throws Exception{
        User user = TestUtils.testUser;
        when(authenticationService.authenticate(any(String.class), any(String.class))).thenReturn(user);

        sessionManager.login("", "");

        verify(authenticationService, times(1)).authenticate(any(String.class), any(String.class));
        assertTrue(sessionManager.isLoggedIn());
        assertEquals(sessionManager.getUser(), user);
    }

    @Test(expected = UserLoggedInException.class)
    public void testLogin_withLoggedInUser_shouldThrow() throws Exception{
        when(authenticationService.authenticate(any(String.class), any(String.class))).thenReturn(TestUtils.testUser);
        sessionManager.login("", "");
        assertTrue(sessionManager.isLoggedIn());

        sessionManager.login("", "");
    }

    @Test
    public void testLogout_withLoggedInUser_shouldSucceed() throws Exception{
        when(authenticationService.authenticate(any(String.class), any(String.class))).thenReturn(TestUtils.testUser);
        sessionManager.login("", "");
        assertTrue(sessionManager.isLoggedIn());

        sessionManager.logout();
        assertFalse(sessionManager.isLoggedIn());
        assertNull(sessionManager.getUser());
    }
}
