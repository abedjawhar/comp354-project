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
        fail("Not implemented");
    }

    @Test
    public void createUser_withValidUser_shouldSetID(){
        fail("Not implemented");
    }

    @Test
    public void getUser_withNonexistentUsername_shouldReturnNull(){
        fail("Not implemented");
    }

    @Test
    public void getUser_withValidUsername_shouldReturnUser(){
        fail("Not implemented");
    }
}