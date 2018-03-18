package com.github.comp354project.model.sqlite;

import com.github.comp354project.model.exceptions.DatabaseException;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.Connection;

public interface IConnectionProvider {
    JdbcConnectionSource getConnectionSource() throws DatabaseException;
}
