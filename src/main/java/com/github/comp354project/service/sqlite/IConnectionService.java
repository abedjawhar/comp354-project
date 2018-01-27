package com.github.comp354project.service.sqlite;

import java.sql.Connection;

public interface IConnectionService {
    Connection getConnection();
}
