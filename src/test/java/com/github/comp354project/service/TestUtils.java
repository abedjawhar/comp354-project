package com.github.comp354project.service;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.account.remote.RemoteTransaction;
import com.github.comp354project.service.user.User;

public class TestUtils {
    public static final User testUser = User.builder()
            .ID(1)
            .firstName("Hrachya")
            .lastName("Hakobyan")
            .username("admin")
            .password("admin").build();
    public static final RemoteAccount testRemoteAccount = RemoteAccount.builder()
            .ID(1)
            .bankName("TD")
            .type("Checking")
            .balance(15823.12).build();
    public static final RemoteTransaction testRemoteTransaction = RemoteTransaction.builder()
            .ID(1)
            .account(testRemoteAccount)
            .date(1517091082)
            .amount(52.2)
            .type("Transfer")
            .sourceID(null)
            .destinationID(2).build();
    public static final Account testAccount = Account.builder()
            .ID(1)
            .user(testUser)
            .bankName("TD")
            .type("Checking")
            .balance(15823.12)
            .build();
    public static final Transaction testTransaction = Transaction.builder()
            .ID(1)
            .account(testAccount)
            .date(1517091082)
            .amount(52.2)
            .type("Transfer")
            .category("Rent")
            .sourceID(null)
            .destinationID(2).build();
}
