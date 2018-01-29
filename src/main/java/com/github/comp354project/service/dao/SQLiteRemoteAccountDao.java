package com.github.comp354project.service.dao;

import com.github.comp354project.service.account.remote.RemoteTransaction;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.exceptions.InvalidParameterException;
import com.github.comp354project.service.sqlite.IConnectionProvider;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.*;

public class SQLiteRemoteAccountDao implements IRemoteAccountDao {

    private IConnectionProvider connectionProvider;

    private static final Logger logger = LogManager.getLogger(SQLiteRemoteAccountDao.class);

    @Inject
    public SQLiteRemoteAccountDao(IConnectionProvider connectionProvider){
        this.connectionProvider = connectionProvider;
    }

    @Override
    public RemoteAccount getRemoteAccount(Integer ID) throws InvalidParameterException, DatabaseException {
        if(ID == null){
            InvalidParameterException e = InvalidParameterException.builder()
                    .message("Invalid ID").build();
            logger.error(e.toString());
            throw e;
        }
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = connectionProvider.getConnection();
            statement = conn.prepareStatement("SELECT * FROM RemoteAccount WHERE RemoteAccount.id = ?");
            statement.setInt(1, ID);
            resultSet  = statement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            RemoteAccount.RemoteAccountBuilder builder = RemoteAccount.builder()
                    .ID(resultSet.getInt("id"))
                    .bankName(resultSet.getString("bank_name"))
                    .type(resultSet.getString("type"))
                    .balance(resultSet.getDouble("balance"))
                    .currency(resultSet.getString("currency"));
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
            statement = conn.prepareStatement("SELECT * FROM RemoteAccountTransaction WHERE RemoteAccountTransaction.account_id = ?");
            statement.setInt(1, ID);
            resultSet  = statement.executeQuery();

            while(resultSet.next()){
                builder.transaction(RemoteTransaction.builder()
                .ID(resultSet.getInt("id"))
                .accountID(resultSet.getInt("account_id"))
                .date(resultSet.getInt("date"))
                .amount(resultSet.getDouble("amount"))
                .currency(resultSet.getString("currency"))
                .type(resultSet.getString("type"))
                .sourceID(getNullableInteger(resultSet, "source_id"))
                .destinationID(getNullableInteger(resultSet, "destination_id")).build());
            }
            return builder.build();
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException("Failed to query RemoteAccount", e);
        } finally{
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }
    }

    private Integer getNullableInteger(ResultSet rs, String strColName) throws SQLException {
        int nValue = rs.getInt(strColName);
        return rs.wasNull() ? null : nValue;
    }
}
