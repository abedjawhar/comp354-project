package com.github.comp354project.model.validators;

import com.github.comp354project.model.exceptions.ValidationError;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements IEmailValidator {


    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

    @Override
    public List<ValidationError> validateEmail(String email, String message) {
        List<ValidationError> errors = new ArrayList<>();
        if(Strings.isNullOrEmpty(email)){
            return errors;
        }
        if(!validate(email)){
            errors.add(ValidationError.builder()
                    .parameterName("email")
                    .message(message)
                    .parameterValue(email).build());
        }
        return errors;
    }
}
