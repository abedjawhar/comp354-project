package com.github.comp354project.service.sqlite;

import com.github.comp354project.service.exceptions.DatabaseException;

import java.sql.Connection;

public interface IConnectionProvider {
    Connection getConnection() throws DatabaseException;
}
