package com.github.comp354project.service.sqlite;

import com.github.comp354project.service.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class ConnectionService implements IConnectionService {
    private static final String DB_NAME = "jdbc:sqlite:sqlite/mymoney.db";
    private static final Logger logger = LogManager.getLogger(ConnectionService.class);

    @Inject
    public ConnectionService(){
    }

    @Override
    public Connection getConnection() throws DatabaseException{
        try {
            return DriverManager.getConnection(DB_NAME);
        } catch(SQLException e){
            logger.error(e.toString());
            throw new DatabaseException("Failed to get connection", e);
        }
    }
}
