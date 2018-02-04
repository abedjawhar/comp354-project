package com.github.comp354project.service.user;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.google.common.base.Strings;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private Dao<User, Integer> userDao;

    @Inject
    public UserService(Dao<User, Integer> userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(User user) throws ValidationException, DatabaseException {
        if(user == null){
            throw ValidationException.builder()
                    .message("Invalid user")
                    .error(ValidationError.builder()
                        .parameterName("user").build())
                    .build();
        }
        List<ValidationError> errors = validate(user);
        if(!errors.isEmpty()){
            throw ValidationException.builder()
                    .message("Invalid user")
                    .errors(errors).build();
        }
        try{
            userDao.create(user);
            return user;
        } catch(SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        }
    }

    @Override
    public User getUser(String username) throws ValidationException, DatabaseException {
        if(Strings.isNullOrEmpty(username)){
            throw ValidationException.builder()
                    .message("Invalid username")
                    .error(ValidationError.builder()
                            .parameterName("username")
                            .parameterValue(username)
                            .build())
                    .build();
        }
        try{
            List<User> result =  userDao.queryForEq("username", username);
            if(result.isEmpty()){
                return null;
            }
            return result.get(0);
        } catch(SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        }
    }

    private List<ValidationError> validate(User user){
        List<ValidationError> errors = new ArrayList<>();
        if(Strings.isNullOrEmpty(user.getUsername())){
            errors.add(ValidationError.builder()
                        .parameterName("username")
                        .parameterValue(user.getUsername()).build());
        }
        if(Strings.isNullOrEmpty(user.getPassword())){
            errors.add(ValidationError.builder()
                    .parameterName("password")
                    .parameterValue(user.getPassword()).build());
        }
        if(Strings.isNullOrEmpty(user.getFirstName())){
            errors.add(ValidationError.builder()
                    .parameterName("firstName")
                    .parameterValue(user.getFirstName()).build());
        }
        if(Strings.isNullOrEmpty(user.getLastName())){
            errors.add(ValidationError.builder()
                    .parameterName("lastName")
                    .parameterValue(user.getLastName()).build());
        }
        return errors;
    }
}
