package com.github.comp354project.service;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.account.remote.RemoteTransaction;
import com.github.comp354project.service.user.User;

import java.sql.Date;
import java.time.Instant;

public class TestUtils {
    /**
     * Sample Data according to setup.sql, do not modify.
     */
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
            .balance(15823.12)
            .currency("CAD")
            .transaction(RemoteTransaction.builder()
                    .ID(1)
                    .accountID(1)
                    .date(1517091082)
                    .amount(52.2)
                    .currency("CAD")
                    .type("Transfer")
                    .sourceID(null)
                    .destinationID(2).build())
            .transaction(RemoteTransaction.builder()
                    .ID(2)
                    .accountID(1)
                    .date(1517099082)
                    .amount(232.0)
                    .currency("CAD")
                    .type("Transfer")
                    .sourceID(null)
                    .destinationID(3).build()).build();
    // Mirrors the RemoteAccount above
    public static final Account testAccount = Account.builder()
            .ID(1)
            .userID(1)
            .bankName("TD")
            .type("Checking")
            .balance(15823.12)
            .currency("CAD")
            .transaction(Transaction.builder()
                    .ID(1)
                    .accountID(1)
                    .date(Date.from(Instant.ofEpochMilli(1517091082)))
                    .amount(52.2)
                    .currency("CAD")
                    .type("Transfer")
                    .category("Rent")
                    .sourceID(null)
                    .destinationID(2).build())
            .transaction(Transaction.builder()
                    .ID(2)
                    .accountID(1)
                    .date(Date.from(Instant.ofEpochMilli(1517099082)))
                    .amount(232.0)
                    .currency("CAD")
                    .type("Transfer")
                    .category("Leisure")
                    .sourceID(null)
                    .destinationID(3).build()).build();

}
