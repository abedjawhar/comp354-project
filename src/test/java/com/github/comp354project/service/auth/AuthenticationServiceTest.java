package com.github.comp354project.service.auth;

import com.github.comp354project.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testAuthenticate_withInvalidPassword_shouldReturnNull()
    }

    @Test
    public void testAuthenticate_withInvalidUsername_shouldReturnNull(){

    }

    @Test
    public void testAuthenticate_withValidCredentials_shouldReturnUser(){

    }
}
