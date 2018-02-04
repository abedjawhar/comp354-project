package com.github.comp354project.service.validators;

public class ValidatorFactory {
    public static IUsernameValidator usernameValidator(){
        return new EmptyStringValidator();
    }

    public static IPasswordValidator passwordValidator(){
        return new EmptyStringValidator();
    }
}
