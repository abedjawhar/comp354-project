package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationException;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RemoteAccountServiceTest {
    private static final Logger logger = LogManager.getLogger(DatabaseException.class);

    @Mock
    private Dao<RemoteAccount, Integer> remoteAccountDao;

    @InjectMocks RemoteAccountService remoteAccountService;

    @Test(expected = ValidationException.class)
    public void testGetAccount_withNullRequest_shouldThrow(){
        remoteAccountService.getAccount(null);
    }

    @Test(expected = ValidationException.class)
    public void testGetAccount_withInvalidRequest_shouldThrow(){
        remoteAccountService.getAccount(GetRemoteAccountRequest.builder().build());
    }
}
