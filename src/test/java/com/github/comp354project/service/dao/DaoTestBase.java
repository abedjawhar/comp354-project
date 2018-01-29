package com.github.comp354project.service.dao;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.sqlite.ConnectionProvider;
import com.github.comp354project.service.sqlite.IConnectionProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.mockito.Mockito.when;

public abstract class DaoTestBase {
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
