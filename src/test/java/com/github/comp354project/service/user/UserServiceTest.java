package com.github.comp354project.service.user;

import com.github.comp354project.service.dao.IUserDao;
import com.github.comp354project.service.exceptions.InvalidParameterException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static junit.framework.TestCase.fail;

public class UserServiceTest {
    @Mock
    IUserDao userDao;

    @InjectMocks
    UserService userService;

    @Test(expected = InvalidParameterException.class)
    public void createUser_withNullUser_shouldThrow(){

    }

    @Test(expected = InvalidParameterException.class)
    public void createUser_withInvalidUser_shouldThrow(){

    }

    @Test
    public void createUser_withValidUser_shouldSucceed(){

    }

    @Test(expected = InvalidParameterException.class)
    public void getUser_withNullUsername_shouldThrow(){

    }

    @Test(expected = InvalidParameterException.class)
    public void getUser_withEmptyUsername_shouldThrow(){

    }

    @Test
    public void getUser_withNonexistentUsername_shouldReturnNull(){

    }

    @Test
    public void getUser_withValidUsername_shouldReturnUser(){

    }
}
