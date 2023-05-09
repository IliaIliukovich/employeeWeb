package com.telran.employeeweb.exceptionhandler;

import com.telran.employeeweb.model.entity.User;
import com.telran.employeeweb.validator.UserValidator;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final UserValidator validator;

    @Autowired
    public GlobalExceptionHandler(UserValidator validator) {
        this.validator = validator;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
            String errorMessage = error.getCode();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
        ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>("General Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @InitBinder
    public void addBinder(WebDataBinder binder) {
        if (binder.getTarget() != null && binder.getTarget().getClass().equals(User.class)) {
            binder.addValidators(validator);
        }
    }

}
