package com.github.comp354project.model.account;

import com.github.comp354project.model.account.exceptions.AccountDoesNotExistException;
import com.github.comp354project.model.account.exceptions.AccountExistsException;
import com.github.comp354project.model.account.remote.*;
import com.github.comp354project.model.exceptions.DatabaseException;
import com.github.comp354project.model.exceptions.ValidationError;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountService implements IAccountService {
    private static final Logger logger = LogManager.getLogger(AccountService.class);

    private Dao<Transaction, Integer> transactionDao;
    private Dao<Account, Integer> accountDao;
    private Dao<User, Integer> userDao;
    private IRemoteAccountService remoteAccountService;

    @Inject
    public AccountService(Dao<Account, Integer>  accountDao,
                          Dao<User, Integer> userDao,
                          Dao<Transaction, Integer> transactionDao,
                          IRemoteAccountService remoteAccountService) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transactionDao = transactionDao;
        this.remoteAccountService = remoteAccountService;
    }

    @Override
    public Account addAccount(GetRemoteAccountRequest request, User user) throws ValidationException, DatabaseException {
        List<ValidationError> errors = new ArrayList<>();
        if(request == null){
            errors.add(ValidationError.builder()
                    .message("Invalid request")
                    .parameterName("request").build());
        }
        else if(request.getAccountID() == null){
            errors.add(ValidationError.builder()
                    .message("Invalid request account ID")
                    .parameterName("request.accountID").build());
        }
        if(user == null){
            errors.add(ValidationError.builder()
                    .message("Invalid user")
                    .parameterName("user").build());
        } else {
            try{
                if(userDao.queryForId(user.getID()) == null){
                    errors.add(ValidationError.builder()
                            .message("Invalid user")
                            .parameterValue(user.toString())
                            .parameterName("user").build());
                }
            } catch (SQLException e){
                logger.error(e);
                throw new DatabaseException(e);
            }
        }
        if(!errors.isEmpty()){
            throw ValidationException.builder()
                    .message("Failed to add account")
                    .errors(errors).build();
        }
        GetRemoteAccountResponse response = remoteAccountService.getAccount(request);
        if(response.getAccount() == null){
            throw AccountDoesNotExistException.builder()
                    .message("Account does not exist.")
                    .request(request).build();
        }
        try{
            RemoteAccount remoteAccount = response.getAccount();
            Account account = transform(remoteAccount);
            {
                Account existingAccount = accountDao.queryForId(account.getID());
                if (existingAccount != null) {
                    throw AccountExistsException.builder()
                            .message("Account already exists.")
                            .account(existingAccount).build();
                }
            }
            account.setUser(user);
            accountDao.create(account);
            for(RemoteTransaction remoteTransaction : remoteAccount.getTransactions()){
                Transaction transaction = transform(remoteTransaction);
                transaction.setAccount(account);
                transactionDao.create(transaction);
            }
            return accountDao.queryForId(account.getID());
        } catch(SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        } catch(AccountExistsException e){
            throw e;
        }
    }

    @Override
    public void deleteAccountsForUser(Integer userID) throws ValidationException {
        if(userID == null){
            throw ValidationException.builder().message("Failed to delete accounts")
                    .error(ValidationError.builder()
                            .parameterName("userID")
                            .message("userID is null").build()).build();
        }
        try{
            List<Account> accountsToDelete = accountDao.queryForEq("user_id", userID);
            List<Transaction> transactions = new ArrayList<>();
            for(Account account : accountsToDelete){
                transactions.addAll(transactionDao.queryForEq("account_id", account));
            }
            accountDao.delete(accountsToDelete);
            transactionDao.delete(transactions);
        } catch (SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteAccount(Account account) throws ValidationException, DatabaseException {
        if(account == null || account.getID() == null) {
            throw ValidationException.builder().message("Null value given in place of Account or Account ID.").build();
        }
        try {
            Account accountInDB = accountDao.queryForId(account.getID());
            if(accountInDB == null) { throw ValidationException.builder().message("Non existent account given to delete! ID: "
                    + account.getID().toString() ).build();}
            List<Transaction> transactionsToDelete = transactionDao.queryForEq("account_id", account);
            for(Transaction t : transactionsToDelete){
                transactionDao.delete(t);
            }
            accountDao.delete(account);
        }
        catch(SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        }
    }

    private Account transform(RemoteAccount remoteAccount){
        Account.AccountBuilder builder = Account.builder()
                .ID(remoteAccount.getID())
                .bankName(remoteAccount.getBankName())
                .type(remoteAccount.getType())
                .balance(remoteAccount.getBalance());
        return builder.build();
    }

    private Transaction transform(RemoteTransaction remoteTransaction){
        return Transaction.builder()
                .ID(remoteTransaction.getID())
                .type(remoteTransaction.getType())
                .amount(remoteTransaction.getAmount())
                .date(remoteTransaction.getDate())
                .sourceID(remoteTransaction.getSourceID())
                .destinationID(remoteTransaction.getDestinationID()).build();
    }
}
