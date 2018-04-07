package com.github.comp354project.model.sqlite;

import com.github.comp354project.model.exceptions.DatabaseException;
import com.github.comp354project.utils.Timing;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.Connection;

public interface IConnectionProvider {
    @Timing
    JdbcConnectionSource getConnectionSource() throws DatabaseException;
}
