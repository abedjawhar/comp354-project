package com.github.comp354project.model.validators;

import com.github.comp354project.BusinessRulesConstants;

public class ValidatorFactory {
    public static IUsernameValidator usernameValidator(){
        return new StringLengthValidator(BusinessRulesConstants.USERNAME_MIN_LENGTH, BusinessRulesConstants.USERNAME_MAX_LENGTH);
    }

    public static IPasswordValidator passwordValidator(){
        return new StringLengthValidator(BusinessRulesConstants.PASSWORD_MIN_LENGTH, BusinessRulesConstants.PASSWORD_MAX_LENGTH);
    }

    public static ICategoryNameValidator categoryNameValidator() {return new StringLengthValidator(BusinessRulesConstants.CATEGORY_MIN_LENGTH, BusinessRulesConstants.CATEGORY_MAX_LENGTH);}

    public static IUserValidator userValidator() { return new UserValidator(usernameValidator(), passwordValidator(), new StringLengthValidator(1, 16));}
}
