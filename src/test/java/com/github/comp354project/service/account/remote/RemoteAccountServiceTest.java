package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RemoteAccountServiceTest {
    private static final Logger logger = LogManager.getLogger(DatabaseException.class);

    private JdbcConnectionSource connectionSource;
    private Dao<RemoteAccount, Integer> accountDao;

    RemoteAccountService remoteAccountService;

    @Before
    public void setUp() throws Exception{
        connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        accountDao = DaoManager.createDao(connectionSource, RemoteAccount.class);
        remoteAccountService = new RemoteAccountService(accountDao);
        TableUtils.createTable(connectionSource, RemoteTransaction.class);
        TableUtils.createTable(connectionSource, RemoteAccount.class);
    }

    @Test(expected = ValidationException.class)
    public void testGetAccount_withNullRequest_shouldThrow() throws ValidationException{
        remoteAccountService.getAccount(null);
    }

    @Test(expected = ValidationException.class)
    public void testGetAccount_withInvalidRequest_shouldThrow() throws ValidationException{
        remoteAccountService.getAccount(GetRemoteAccountRequest.builder().build());
    }

    @Test
    public void testGetAccount_withExistingAccount_shouldReturnValidAccount() throws Exception{
        RemoteAccount expectedAccount = TestUtils.createTestRemoteAccount(connectionSource);
        GetRemoteAccountRequest request = GetRemoteAccountRequest.builder().accountID(expectedAccount.getID()).build();

        GetRemoteAccountResponse response = remoteAccountService.getAccount(request);
        RemoteAccount actualAccount = response.getAccount();

        assertEquals(expectedAccount, actualAccount);
        assertEquals(expectedAccount.getTransactions().size(), actualAccount.getTransactions().size());
    }


}
