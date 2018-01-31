package com.github.comp354project.service.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtils {
    public static Integer getNullableInteger(ResultSet rs, String strColName) throws SQLException {
        int nValue = rs.getInt(strColName);
        return rs.wasNull() ? null : nValue;
    }
}
