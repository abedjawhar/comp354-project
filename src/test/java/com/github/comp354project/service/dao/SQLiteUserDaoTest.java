package com.github.comp354project.service.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.fail;


@RunWith(MockitoJUnitRunner.class)
public class SQLiteUserDaoTest extends DaoTestBase{
    @InjectMocks
    private SQLiteUserDao dao;

    @Test
    public void createUser_withValidUser_shouldSucceed(){

    }

    @Test
    public void createUser_withValidUser_shouldSetID(){

    }

    @Test
    public void getUser_withNonexistentUsername_shouldReturnNull(){

    }

    @Test
    public void getUser_withValidUsername_shouldReturnUser(){

    }
}