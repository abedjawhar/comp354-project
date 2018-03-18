package com.github.comp354project.model;

import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.account.remote.RemoteAccount;
import com.github.comp354project.model.account.remote.RemoteTransaction;
import com.github.comp354project.model.user.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;

public class TestUtils {
    public static final User testUser = User.builder()
            .ID(1)
            .firstName("Hrachya")
            .lastName("Hakobyan")
            .username("admin")
            .password("admin")
            .email("sample@email.com")
            .address("address")
            .phone("111111").build();
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
            .ID(10)
            .account(testAccount)
            .date(1517091082)
            .amount(52.2)
            .type("Transfer")
            .category("Rent")
            .sourceID(null)
            .destinationID(2).build();

    public static RemoteAccount createTestRemoteAccount(JdbcConnectionSource connectionSource) throws SQLException {
        Dao<RemoteAccount, Integer> remoteAccountDao = DaoManager.createDao(connectionSource, RemoteAccount.class);
        Dao<RemoteTransaction, Integer> remoteTransactionDao = DaoManager.createDao(connectionSource, RemoteTransaction.class);

        RemoteAccount remoteAccount = TestUtils.testRemoteAccount;
        remoteAccountDao.create(remoteAccount);
        RemoteTransaction remoteTransaction = TestUtils.testRemoteTransaction;
        remoteTransactionDao.create(remoteTransaction);

        return remoteAccountDao.queryForId(remoteAccount.getID());
    }
}
