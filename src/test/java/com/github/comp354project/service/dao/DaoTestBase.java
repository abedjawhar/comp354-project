package com.github.comp354project.service.dao;

import com.github.comp354project.service.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public abstract class DaoTestBase {
    private static final Logger logger = LogManager.getLogger(DatabaseException.class);
    protected Connection connection;

    protected static final String TABLE_NAME_REMOTE_ACCOUNT = "RemoteAccount";
    protected static final String TABLE_NAME_REMOTE_TRANSACTION = "RemoteAccountTransaction";
    protected static final String TABLE_NAME_ACCOUNT = "Account";
    protected static final String TABLE_NAME_TRANSACTION = "AccountTransaction";
    protected static final String TABLE_NAME_USER = "User";
    /**
     * Purge the tables before each DAO test
     */
    @Before
    public void setUp() throws Exception{
        String dbName = "jdbc:sqlite:" + System.getenv("db");
        connection = DriverManager.getConnection(dbName);

        String sql = "DELETE FROM %s";
        PreparedStatement statement = connection.prepareStatement(String.format(sql, TABLE_NAME_REMOTE_TRANSACTION));
        statement.execute();
        statement = connection.prepareStatement(String.format(sql, TABLE_NAME_REMOTE_ACCOUNT));
        statement.execute();
        statement = connection.prepareStatement(String.format(sql, TABLE_NAME_TRANSACTION));
        statement.execute();
        statement = connection.prepareStatement(String.format(sql, TABLE_NAME_ACCOUNT));
        statement.execute();
        statement = connection.prepareStatement(String.format(sql, TABLE_NAME_USER));
        statement.execute();
    }
}
