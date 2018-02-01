package com.github.comp354project.service.dao;

import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.*;


@RunWith(MockitoJUnitRunner.class)
public class SQLiteUserDaoTest extends DaoTestBase{
    @InjectMocks
    private SQLiteUserDao dao;

    @Test
    public void createUser_withValidUser_shouldReturnValidUser(){
        User expectedUser = User.builder()
                .username("USERNAME")
                .password("PASSOWORD")
                .firstName("FIRSTNAME")
                .lastName("LASTNAME").build();

        User actualUser = dao.createUser(expectedUser);

        assertNotNull(actualUser.getID());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(), actualUser.getLastName());
    }

    @Test
    public void getUser_withNonexistentUsername_shouldReturnNull(){
        assertNull(dao.getUser("NONEXISTENT_USERNAME"));
    }

    @Test
    public void getUser_withValidUsername_shouldReturnUser(){
        User user = dao.getUser(TestUtils.testUser.getUsername());

        assertEquals(TestUtils.testUser, user);
    }
}