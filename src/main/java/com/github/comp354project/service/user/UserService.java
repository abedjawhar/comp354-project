package com.github.comp354project.service.user;

import com.github.comp354project.service.account.Account;
import com.github.comp354project.service.account.AccountService;
import com.github.comp354project.service.account.exceptions.AuthenticationException;
import com.github.comp354project.service.account.exceptions.AuthorisationException;
import com.github.comp354project.service.auth.SessionManager;
import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.github.comp354project.service.user.exceptions.UserExistsException;
import com.github.comp354project.service.validators.IUsernameValidator;
import com.github.comp354project.service.validators.ValidatorFactory;
import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private Dao<User, Integer> userDao;
    private IUsernameValidator usernameValidator;
    private SessionManager sessionManager;
    private AccountService accountService;

    @Inject
    public UserService(Dao<User, Integer> userDao, SessionManager sessionManager, AccountService accountService) {
        this.userDao = userDao;
        this.sessionManager = sessionManager;
        this.usernameValidator = ValidatorFactory.usernameValidator();
        this.accountService = accountService;
    }

    @Override
    public User createUser(User user) throws UserExistsException, ValidationException, DatabaseException {
        if (user == null) {
            throw ValidationException.builder()
                    .message("Invalid user")
                    .error(ValidationError.builder()
                            .parameterName("user").build())
                    .build();
        }
        List<ValidationError> errors = validate(user);
        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .message("Invalid user")
                    .errors(errors).build();
        }
        try {
            User existingUser = getUser(user.getUsername());
            if (existingUser != null) {
                throw UserExistsException.builder()
                        .message("User already exists")
                        .user(existingUser).build();
            }
            userDao.create(user);
            return user;
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException(e);
        } catch (UserExistsException e) {
            throw e;
        }
    }

    @Override
    public User getUser(String username) throws ValidationException, DatabaseException {
        List<ValidationError> errors = this.usernameValidator.validateUsername(username, "Invalid username");
        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .message("Failed to get user.")
                    .errors(errors).build();
        }
        try {
            List<User> result = userDao.queryForEq("username", username);
            if (result.isEmpty()) {
                return null;
            }
            return result.get(0);
        } catch (SQLException e) {
            logger.error(e);
            throw new DatabaseException(e);
        }
    }

    private List<ValidationError> validate(User user) {
        List<ValidationError> errors = new ArrayList<>();
        if (Strings.isNullOrEmpty(user.getUsername())) {
            errors.add(ValidationError.builder()
                    .parameterName("username")
                    .message("Username required")
                    .parameterValue(user.getUsername()).build());
        }
        if (Strings.isNullOrEmpty(user.getPassword())) {
            errors.add(ValidationError.builder()
                    .parameterName("password")
                    .message("Password required")
                    .parameterValue(user.getPassword()).build());
        }
        if (Strings.isNullOrEmpty(user.getFirstName())) {
            errors.add(ValidationError.builder()
                    .parameterName("firstName")
                    .message("Firstname required")
                    .parameterValue(user.getFirstName()).build());
        }
        if (Strings.isNullOrEmpty(user.getLastName())) {
            errors.add(ValidationError.builder()
                    .parameterName("lastName")
                    .message("Lastname required")
                    .parameterValue(user.getLastName()).build());
        }
        return errors;
    }

   @Override
   public void deleteBankAccount(Account account) {
        if(account == null) throw ValidationException.builder()
                .message("Null value given in place of Account or Account ID.").build();
        if(!sessionManager.isLoggedIn()) throw AuthenticationException.builder()
                .message("Tried to delete account while not logged in!")
                .password("")
                .username("")
                .build();
        if(sessionManager.getUser().getID() != account.getUser().getID()) throw AuthorisationException.builder()
                .message("Tried to delete account that is not owned by the logged in user!")
                .user(sessionManager.getUser())
                .build();
        accountService.deleteAccount(account);
    }
}