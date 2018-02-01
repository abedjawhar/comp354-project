package com.github.comp354project.service.dao;


import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.exceptions.DatabaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SQLiteAccountDaoTest extends DaoTestBase{
    @InjectMocks
    private SQLiteAccountDao dao;

    @Test
    public void getAccounts_withNonexistentUserID_shouldReturnEmptyList(){
        List<Account> accountList = dao.getAccounts(1000000);

        assertNotNull(accountList);
        assertEquals(0, accountList.size());
    }

    @Test
    public void getAccounts_withExistingUserID_shouldReturnValidList(){
        List<Account> accountList = dao.getAccounts(1);

        assertNotNull(accountList);
        assertFalse(accountList.isEmpty());
    }

    @Test(expected = DatabaseException.class)
    public void createAccount_withExistingID_shouldThrow(){
        dao.createAccount(TestUtils.testAccount); // TestAccount is already in the test database
    }

    @Test
    public void createAccount_withNonexistentID_shouldSucceed(){
        Account account = TestUtils.testAccount;
        account.setID(100000);

        assertNotNull(dao.createAccount(account));
    }
}
