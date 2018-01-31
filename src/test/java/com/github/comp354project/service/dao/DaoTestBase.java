package com.github.comp354project.service.dao;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.account.remote.RemoteTransaction;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.sqlite.ConnectionProvider;
import com.github.comp354project.service.sqlite.IConnectionProvider;
import com.github.comp354project.service.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Instant;

import static org.mockito.Mockito.when;

public abstract class DaoTestBase {
    /**
     * Sample Data according to setup.sql, do not modify.
     */
    protected static final User testUser = User.builder()
            .ID(1)
            .firstName("Hrachya")
            .lastName("Hakobyan")
            .username("admin")
            .password("admin").build();
    protected static final RemoteAccount testRemoteAccount = RemoteAccount.builder()
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
    protected static final Account testAccount = Account.builder()
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

    protected static final Logger logger = LogManager.getLogger(DatabaseException.class);

    protected Connection connection;

    @Mock
    protected IConnectionProvider connectionProvider;

    @Before
    public void setUp() throws Exception{
        if(connection != null){
            connection.close();
        }
        connection = DriverManager.getConnection("jdbc:sqlite:");
        Statement stat = connection.createStatement();
        stat.executeUpdate(String.format("restore from %s", System.getenv("db")));

        when(connectionProvider.getConnection()).thenReturn(connection);
    }
}
