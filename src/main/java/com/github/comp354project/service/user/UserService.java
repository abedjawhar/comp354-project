package com.github.comp354project.service.user;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.AccountService;
import com.github.comp354project.service.auth.exceptions.AuthenticationException;
import com.github.comp354project.service.auth.exceptions.AuthorisationException;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.validators.IUserValidator;
import com.github.comp354project.service.validators.IUsernameValidator;
import com.github.comp354project.service.validators.ValidatorFactory;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private Dao<User, Integer> userDao;
    private IUsernameValidator usernameValidator;
    private IUserValidator userValidator;
    private SessionManager sessionManager;
    private AccountService accountService;

    @Inject
    public UserService(Dao<User, Integer> userDao, SessionManager sessionManager, AccountService accountService) {
        this.userDao = userDao;
        this.sessionManager = sessionManager;
        this.usernameValidator = ValidatorFactory.usernameValidator();
        this.userValidator = ValidatorFactory.userValidator();
        this.accountService = accountService;
    }

    @Override
    public User createUser(User user) throws ValidationException, DatabaseException {
        List<ValidationError> errors = this.userValidator.validateUser(user);
        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .message("Failed to create user")
                    .errors(errors).build();
        }
        try {
            User existingUser = getUser(user.getUsername());
            if (existingUser != null) {
                throw ValidationException.builder()
                        .error(ValidationError.builder()
                                .parameterName("user")
                                .parameterValue(existingUser.toString())
                                .message("User already exists").build())
                        .message("Failed to create user").build();
            }
            userDao.create(user);
            return user;
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException(e);
        }
    }

    private User getUser(String username) throws SQLException{
        List<User> result = userDao.queryForEq("username", username);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

   @Override
   public void deleteBankAccount(Account account) throws ValidationException, AuthenticationException, AuthorisationException{
        if(account == null || account.getID() == null)
            throw ValidationException.builder().message("Null value given in place of Account or Account ID.").build();
        if(!sessionManager.isLoggedIn()) throw AuthenticationException.builder()
                .message("Tried to delete account while not logged in!")
                .password("")
                .username("")
                .build();
        if(!sessionManager.getUser().getID().equals(account.getUser().getID())) throw AuthorisationException.builder()
                .message("Tried to delete account that is not owned by the logged in user!")
                .user(sessionManager.getUser())
                .build();
        accountService.deleteAccount(account);
    }
}