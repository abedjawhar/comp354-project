package com.github.comp354project.service.dao;

import com.github.comp354project.service.account.remote.RemoteAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class SQLiteRemoteAccountDaoTest extends DaoTestBase{
    @InjectMocks private SQLiteRemoteAccountDao dao;

    @Test
    public void getRemoteAccount_withNonexistentID_shouldReturnNull(){
        assertNull(dao.getRemoteAccount(1234234));
    }

    @Test
    public void getRemoteAccount_withValidID_shouldReturnObject(){
        RemoteAccount acc = dao.getRemoteAccount(1);
        assertNotNull(acc);
    }
}
