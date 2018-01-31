package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.dao.IRemoteAccountDao;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoteAccountServiceTest {
    protected static final Logger logger = LogManager.getLogger(DatabaseException.class);

    @Mock
    private IRemoteAccountDao remoteAccountDao;

    @InjectMocks RemoteAccountService remoteAccountService;

    @Test(expected = ValidationException.class)
    public void getAccount_withNullRequest_shouldThrow(){
        remoteAccountService.getAccount(null);
    }

    @Test(expected = ValidationException.class)
    public void getAccount_withInvalidRequest_shouldThrow(){
        remoteAccountService.getAccount(GetRemoteAccountRequest.builder().build());
    }

    @Test
    public void getAccount_withNonexistentAccountID_shouldReturnEmptyResponse(){
        Integer nonexistentID = 100000;
        GetRemoteAccountRequest remoteAccountRequest = GetRemoteAccountRequest.builder().
                accountID(nonexistentID).build();
        when(remoteAccountDao.getRemoteAccount(Mockito.eq(nonexistentID))).thenReturn(null);

        GetRemoteAccountResponse remoteAccountResponse = remoteAccountService.getAccount(remoteAccountRequest);

        assertNotNull(remoteAccountResponse);
        assertNull(remoteAccountResponse.getAccount());
    }

    @Test
    public void getAccount_withValidAccountID_shouldReturnValidResponse() {
        Integer validID = 1;
        GetRemoteAccountRequest remoteAccountRequest = GetRemoteAccountRequest.builder()
                .accountID(validID).build();
        when(remoteAccountDao.getRemoteAccount(Mockito.eq(validID))).thenReturn(RemoteAccount.builder().build());

        GetRemoteAccountResponse remoteAccountResponse = remoteAccountService.getAccount(remoteAccountRequest);

        assertNotNull(remoteAccountResponse);
        assertNotNull(remoteAccountResponse.getAccount());
    }
}
