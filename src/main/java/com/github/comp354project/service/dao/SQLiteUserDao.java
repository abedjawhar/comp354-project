package com.github.comp354project.service.dao;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.sqlite.IConnectionProvider;
import com.github.comp354project.service.user.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteUserDao implements IUserDao{
    private static final Logger logger = LogManager.getLogger(SQLiteRemoteAccountDao.class);

    private IConnectionProvider connectionProvider;

    @Inject
    public SQLiteUserDao(IConnectionProvider connectionProvider){
        this.connectionProvider = connectionProvider;
    }

    @Override
    public User createUser(User user) throws DatabaseException {
        Connection conn = null;
        PreparedStatement insertStatement = null;
        PreparedStatement getStatement = null;
        ResultSet resultSet = null;
        try {
            conn = connectionProvider.getConnection();
            insertStatement = conn.prepareStatement("INSERT INTO User(username, password, first_name, last_name) VALUES(?,?,?,?)");
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getPassword());
            insertStatement.setString(3, user.getFirstName());
            insertStatement.setString(4, user.getLastName());
            insertStatement.executeUpdate();

            getStatement = conn.prepareStatement("SELECT * FROM User WHERE User.username = ?");
            getStatement.setString(1, user.getUsername());
            resultSet = getStatement.executeQuery();
            return transform(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException("Failed to create User", e);
        } finally{
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(insertStatement);
            DbUtils.closeQuietly(getStatement);
            DbUtils.closeQuietly(conn);
        }
    }

    @Override
    public User getUser(String username) throws DatabaseException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = connectionProvider.getConnection();
            statement = conn.prepareStatement("SELECT * FROM User WHERE User.username = ?");
            statement.setString(1, username);
            resultSet  = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            return transform(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException("Failed to query User", e);
        } finally{
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }
    }

    private User transform(ResultSet resultSet) throws SQLException{
        return User.builder()
                .ID(resultSet.getInt("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name")).build();
    }
}