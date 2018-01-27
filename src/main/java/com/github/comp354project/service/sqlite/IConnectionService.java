package com.github.comp354project.service.sqlite;

import com.github.comp354project.service.DatabaseException;

import java.sql.Connection;

public interface IConnectionService {
    Connection getConnection() throws DatabaseException;
}
