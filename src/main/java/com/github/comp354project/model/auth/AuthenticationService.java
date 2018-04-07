package com.github.comp354project.model.auth;

import com.github.comp354project.model.exceptions.DatabaseException;
import com.github.comp354project.model.exceptions.ValidationError;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import com.github.comp354project.model.validators.IPasswordValidator;
import com.github.comp354project.model.validators.IUsernameValidator;
import com.github.comp354project.model.validators.ValidatorFactory;
import com.github.comp354project.utils.Timing;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Timing
public class AuthenticationService implements IAuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    private Dao<User, Integer> userDao;

    private IUsernameValidator usernameValidator;

    private IPasswordValidator passwordValidator;

    @Inject
    public AuthenticationService(Dao<User, Integer> userDao){
        this.userDao = userDao;
        this.usernameValidator = ValidatorFactory.usernameValidator();
        this.passwordValidator  = ValidatorFactory.passwordValidator();
    }

    @Override
    public User authenticate(String username, String password) throws ValidationException {
        List<ValidationError> validationErrors = new ArrayList<>();
        validationErrors.addAll(this.usernameValidator.validateUsername(username, "Invalid username"));
        validationErrors.addAll(this.passwordValidator.validatePassword(password, "Invalid password"));
        if(!validationErrors.isEmpty()){
            throw ValidationException.builder()
                    .message("Failed to authenticate user")
                    .errors(validationErrors).build();
        }
        User user = getUser(username);
        if(user == null){
            throw ValidationException.builder()
                    .message("Failed to authenticate user.")
                    .error(ValidationError.builder()
                            .message("Invalid username")
                            .parameterName("username")
                            .parameterValue(username).build())
                    .build();
        }
        if(!user.getPassword().equals(password)){
            throw ValidationException.builder()
                    .message("Failed to authenticate user.")
                    .error(ValidationError.builder()
                            .message("Incorrect password")
                            .parameterName("password")
                            .parameterValue(password).build())
                    .build();
        }
        return user;
    }
    private User getUser(String username){
            try{
                List<User> users = userDao.queryForEq("username", username);
                if(users.isEmpty()){
                    return null;
                }
                else return users.get(0);
            }
            catch(SQLException e){
                logger.error(e);
                throw new DatabaseException(e);
            }
        }
}
