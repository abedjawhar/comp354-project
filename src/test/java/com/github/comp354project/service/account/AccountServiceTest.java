package com.github.comp354project.service.account;


import com.github.comp354project.service.TestUtils;
import com.github.comp354project.service.account.remote.GetRemoteAccountRequest;
import com.github.comp354project.service.account.remote.GetRemoteAccountResponse;
import com.github.comp354project.service.account.remote.IRemoteAccountService;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.dao.IAccountDao;
import com.github.comp354project.service.exceptions.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private IAccountDao accountDao;

    @Mock
    private IRemoteAccountService remoteAccountService;

    @InjectMocks
    private AccountService accountService;

    @Test(expected = ValidationException.class)
    public void getAccounts_withNullID_shouldThrow(){

    }

    @Test
    public void getAccounts_withNonexistentID_shouldReturnEmptyList(){
        Integer ID = 1000000;
        when(accountDao.getAccounts(eq(ID))).thenReturn(new ArrayList<>());

        List<Account> accountList = accountService.getAccounts(ID);
        assertNotNull(accountList);
        assertEquals(0, accountList.size());
    }

    @Test(expected = ValidationException.class)
    public void addAccount_withNullRequest_shouldThrow(){
        accountService.addAccount(null);
    }

    @Test(expected = ValidationException.class)
    public void addAccount_withInvalidRequest_shouldThrow(){
        accountService.addAccount(GetRemoteAccountRequest.builder().build());
    }

    @Test
    public void addAccount_withNonexistentRemoteAccount_shouldReturnNull(){
        GetRemoteAccountRequest request = GetRemoteAccountRequest.builder().accountID(100000).build();
        when(remoteAccountService.getAccount(eq(request))).thenReturn(GetRemoteAccountResponse.builder().build());

        assertNull(accountService.addAccount(request));
    }

    @Test
    public void addAccount_withExistingRemoteAccount_shouldSaveAccountAndReturnValidAccount(){
        RemoteAccount remoteAccount = TestUtils.testRemoteAccount;
        Account account = TestUtils.testAccount;
        GetRemoteAccountRequest request = GetRemoteAccountRequest.builder()
                .accountID(remoteAccount.getID()).build();
        GetRemoteAccountResponse response = GetRemoteAccountResponse.builder()
                .account(remoteAccount).build();

        when(remoteAccountService.getAccount(any(GetRemoteAccountRequest.class))).thenReturn(response);
        when(accountDao.createAccount(any(Account.class))).thenReturn(account);

        assertNotNull(accountService.addAccount(request));
    }
}