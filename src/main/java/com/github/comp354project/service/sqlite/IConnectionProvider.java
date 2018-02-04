package com.github.comp354project.service.sqlite;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.Connection;

public interface IConnectionProvider {
    JdbcConnectionSource getConnectionSource() throws DatabaseException;
}
