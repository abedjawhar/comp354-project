package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.dao.IRemoteAccountDao;
import com.github.comp354project.service.exceptions.InvalidParameterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class RemoteAccountServiceTest {

    @Mock
    private IRemoteAccountDao remoteAccountDao;

    @InjectMocks RemoteAccountService remoteAccountService;

    @Test(expected = InvalidParameterException.class)
    public void getAccount_withNullRequest_shouldThrow(){

    }

    @Test(expected = InvalidParameterException.class)
    public void getAccount_withInvalidRequest_shouldThrow(){

    }

    @Test
    public void getAccount_withNonexistentAccount_shouldReturnEmptyResponse(){

    }

    @Test
    public void getAccount_withValidAccount_shouldReturnValidResponse() {

    }
}
