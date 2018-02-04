package com.github.comp354project.service.account.remote;

import com.github.comp354project.service.account.AccountService;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;

public class RemoteAccountService implements IRemoteAccountService {
    private static final Logger logger = LogManager.getLogger(RemoteAccountService.class);

    private Dao<RemoteAccount, Integer> remoteAccountDao;

    @Inject
    public RemoteAccountService(Dao<RemoteAccount, Integer> remoteAccountDao){
        this.remoteAccountDao = remoteAccountDao;
    }

    @Override
    public GetRemoteAccountResponse getAccount(GetRemoteAccountRequest request) throws ValidationException {
        if(request == null){
            throw ValidationException.builder()
                    .message("Null request")
                    .error(ValidationError.builder()
                            .parameterName("request")
                            .build()).build();
        }
        if(request.getAccountID() == null){
            throw ValidationException.builder()
                    .message("Invalid request")
                    .error(ValidationError.builder()
                            .parameterName("accountID")
                            .build()).build();
        }
        try{
            RemoteAccount account = remoteAccountDao.queryForId(request.getAccountID());
            return GetRemoteAccountResponse.builder().account(account).build();
        } catch (SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        }
    }
}
