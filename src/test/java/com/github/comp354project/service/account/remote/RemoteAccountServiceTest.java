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
        fail("Not implemented");
    }

    @Test(expected = InvalidParameterException.class)
    public void getAccount_withInvalidRequest_shouldThrow(){
        fail("Not implemented");
    }

    @Test
    public void getAccount_withNonexistentAccount_shouldReturnEmptyResponse(){
        fail("Not implemented");
    }

    @Test
    public void getAccount_withValidAccount_shouldReturnValidResponse() {
        fail("Not implemented");
    }
}
