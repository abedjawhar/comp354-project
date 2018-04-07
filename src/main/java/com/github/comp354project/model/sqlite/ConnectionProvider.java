package com.github.comp354project.model.sqlite;

import com.github.comp354project.model.exceptions.DatabaseException;
import com.github.comp354project.utils.Timing;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

@Singleton
@Timing
public class ConnectionProvider implements IConnectionProvider{
    private static final Logger logger = LogManager.getLogger(ConnectionProvider.class);

    @Inject
    public ConnectionProvider(){
    }

    @Override
    public JdbcConnectionSource getConnectionSource() {
        String dbName = "jdbc:sqlite:sqlite/mymoney.db";
        try {
            return new JdbcConnectionSource(dbName);
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException("Failed to get connection", e);
        }
    }
}
