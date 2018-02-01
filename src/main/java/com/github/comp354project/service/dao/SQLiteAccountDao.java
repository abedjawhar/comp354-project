package com.github.comp354project.service.dao;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.Transaction;
import com.github.comp354project.service.account.remote.RemoteAccount;
import com.github.comp354project.service.account.remote.RemoteTransaction;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.sqlite.ConnectionProvider;
import com.github.comp354project.service.sqlite.IConnectionProvider;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SQLiteAccountDao implements IAccountDao {
    private IConnectionProvider connectionProvider;

    private static final Logger logger = LogManager.getLogger(SQLiteRemoteAccountDao.class);

    @Inject
    public SQLiteAccountDao(IConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public List<Account> getAccounts(Integer userID) throws DatabaseException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = connectionProvider.getConnection();
            statement = conn.prepareStatement("SELECT * FROM Account WHERE Account.user_id = ?");
            statement.setInt(1, userID);
            resultSet  = statement.executeQuery();

            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){
                Integer accountID = resultSet.getInt("id");
                accounts.add(Account.builder()
                        .ID(accountID)
                        .userID(resultSet.getInt("user_id"))
                        .bankName(resultSet.getString("bank_name"))
                        .type(resultSet.getString("type"))
                        .balance(resultSet.getDouble("balance"))
                        .transactions(getTransactions(accountID, conn)).build());
            }
            return accounts;
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException("Failed to query Accounts", e);
        } finally{
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }
    }

    @Override
    public Account createAccount(Account account) throws DatabaseException {
        Connection conn = null;
        PreparedStatement createAccountStatement = null;
        PreparedStatement createTransactionsStatement = null;
        try {
            conn = connectionProvider.getConnection();
            conn.setAutoCommit(false);

            createAccountStatement = conn.prepareStatement("INSERT INTO Account(id, user_id, bank_name, type, balance)" +
                    " VALUES(?, ?, ?, ?, ?");
            createAccountStatement.setInt(1, account.getID());
            createAccountStatement.setInt(2, account.getUserID());
            createAccountStatement.setString(3, account.getBankName());
            createAccountStatement.setString(4, account.getType());
            createAccountStatement.setDouble(5, account.getBalance());
            createAccountStatement.executeUpdate();

            createTransactionsStatement = conn.prepareStatement("INSERT INTO Transaction(id, account_id, date, amount, type, category, source_id, destination_id" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?");
            for(Transaction transaction : account.getTransactions()){
                createTransactionsStatement.setInt(1, "");
            }
            createAccountStatement.executeBatch();
        } catch (SQLException e) {
            if(conn != null){
                try{
                    conn.rollback();
                } catch (SQLException inner){
                    logger.error(e);
                }
            }
            logger.error(e);
            throw new DatabaseException("Failed to query Accounts", e);
        } finally{
            DbUtils.closeQuietly(createAccountStatement);
            DbUtils.closeQuietly(conn);
        }
    }


    private List<Transaction> getTransactions(Integer accountID, Connection conn) throws SQLException{
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM AccountTransaction WHERE AccountTransaction.account_id = ?");
        statement.setInt(1, accountID);
        ResultSet resultSet  = statement.executeQuery();
        List<Transaction> transactions = new ArrayList<>();
        while(resultSet.next()){
           transactions.add(Transaction.builder()
                    .ID(resultSet.getInt("id"))
                    .accountID(resultSet.getInt("account_id"))
                    .date(Date.from(Instant.ofEpochMilli(resultSet.getInt("date"))))
                    .amount(resultSet.getDouble("amount"))
                    .currency(resultSet.getString("currency"))
                    .type(resultSet.getString("type"))
                    .category(resultSet.getString("category"))
                    .sourceID(DaoUtils.getNullableInteger(resultSet, "source_id"))
                    .destinationID(DaoUtils.getNullableInteger(resultSet, "destination_id")).build());
        }
        DbUtils.closeQuietly(resultSet);
        DbUtils.closeQuietly(statement);
        return transactions;
    }
}
