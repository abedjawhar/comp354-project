package com.github.comp354project.model.dao;

import com.github.comp354project.model.account.Account;
import com.github.comp354project.model.account.Transaction;
import com.github.comp354project.model.account.remote.RemoteAccount;
import com.github.comp354project.model.sqlite.IConnectionProvider;
import com.github.comp354project.model.user.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import dagger.Module;
import dagger.Provides;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

@Module
public class DaoModule {
    private static final Logger logger = LogManager.getLogger(DaoModule.class);
    @Provides
    static Dao<RemoteAccount, Integer> provideRemoteAccountDao(IConnectionProvider connectionProvider)  {
        try {
            return DaoManager.createDao(connectionProvider.getConnectionSource(), RemoteAccount.class);
        } catch (SQLException e){
            logger.error(e);
            return null;
        }
    }

    @Provides
    static Dao<Account, Integer> provideAccountDao(IConnectionProvider connectionProvider)  {
        try {
            return DaoManager.createDao(connectionProvider.getConnectionSource(), Account.class);
        } catch (SQLException e){
            logger.error(e);
            return null;
        }
    }

    @Provides
    static Dao<Transaction, Integer> provideTransactionDao(IConnectionProvider connectionProvider)  {
        try {
            return DaoManager.createDao(connectionProvider.getConnectionSource(), Transaction.class);
        } catch (SQLException e){
            logger.error(e);
            return null;
        }
    }

    @Provides
    static Dao<User, Integer> provideUserDao(IConnectionProvider connectionProvider)  {
        try {
            return DaoManager.createDao(connectionProvider.getConnectionSource(), User.class);
        } catch (SQLException e){
            logger.error(e);
            return null;
        }
    }}
