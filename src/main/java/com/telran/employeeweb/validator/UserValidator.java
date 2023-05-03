package com.telran.employeeweb.validator;


import com.telran.employeeweb.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getUsername().length() > 20) {
            errors.rejectValue("username", "Name length should be no more than 45 symbols");
        }
        if (!user.getUsername().matches("[A-Za-z0-9_-]+")){
            errors.rejectValue("username", "Incorrect symbols used");
        }
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "Password length should be no less than 8 symbols");
        }
        if (!user.getPassword().matches("[A-Za-z0-9_-]+")){
            errors.rejectValue("password", "Incorrect symbols used");
        }

    }
}
