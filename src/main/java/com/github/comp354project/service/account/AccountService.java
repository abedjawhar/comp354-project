package com.github.comp354project.service.account;

import com.github.comp354project.service.account.remote.*;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.User;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
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
                    .parameterName("request").build());
        }
        else if(request.getAccountID() == null){
            errors.add(ValidationError.builder()
                    .parameterName("request.accountID").build());
        }
        if(user == null){
            errors.add(ValidationError.builder()
                    .parameterName("user").build());
        }
        if(!errors.isEmpty()){
            throw ValidationException.builder()
                    .message("Failed to add account")
                    .errors(errors).build();
        }
        GetRemoteAccountResponse response = remoteAccountService.getAccount(request);
        if(response.getAccount() == null){
            return null;
        }
        try{
            RemoteAccount remoteAccount = response.getAccount();
            Account account = transform(remoteAccount);
            account.setUser(user);
            accountDao.create(account);
            for(RemoteTransaction remoteTransaction : remoteAccount.getTransactions()){
                Transaction transaction = transform(remoteTransaction);
                transaction.setAccount(account);
                transactionDao.create(transaction);
            }
            return account;
        } catch(SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        }
    }

    private Account transform(RemoteAccount remoteAccount){
        Account.AccountBuilder builder = Account.builder()
                .ID(remoteAccount.getID())
                .bankName(remoteAccount.getBankName())
                .type(remoteAccount.toString())
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
