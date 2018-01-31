package com.github.comp354project.service.user;

import com.github.comp354project.service.exceptions.DatabaseException;
import com.github.comp354project.service.dao.IUserDao;
import com.github.comp354project.service.exceptions.ValidationError;
import com.github.comp354project.service.exceptions.ValidationException;
import com.google.common.base.Strings;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    private IUserDao userDao;

    @Inject
    public UserService(IUserDao userDao) {
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
        return userDao.createUser(user);
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
        return userDao.getUser(username);
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
