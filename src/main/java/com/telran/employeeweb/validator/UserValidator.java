package com.telran.employeeweb.validator;


import com.telran.employeeweb.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final String NAME_LENGTH_LIMIT = "Name length should be no more than 45 symbols";
    private static final String SYMBOLS_LIMIT = "Incorrect symbols used";
    private static final String PASSWORD_LENGTH = "Password length should be no less than 8 symbols";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getUsername().length() > 20) {
            errors.rejectValue("username", NAME_LENGTH_LIMIT, NAME_LENGTH_LIMIT);
        }
        if (!user.getUsername().matches("[A-Za-z0-9_-]+")){
            errors.rejectValue("username", SYMBOLS_LIMIT, SYMBOLS_LIMIT);
        }
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", PASSWORD_LENGTH, PASSWORD_LENGTH);
        }
        if (!user.getPassword().matches("[A-Za-z0-9_-]+")){
            errors.rejectValue("password", SYMBOLS_LIMIT, SYMBOLS_LIMIT);
        }

    }
}
