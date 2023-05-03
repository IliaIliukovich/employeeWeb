package com.telran.employeeweb.controller;

import com.telran.employeeweb.model.entity.User;
import com.telran.employeeweb.service.UserService;
import com.telran.employeeweb.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService service;
    private final UserValidator validator;

    @Autowired
    public UserRestController(UserService service, UserValidator validator) {
        this.validator = validator;
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers(){
        return service.getAll();
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        return service.saveUser(user);
    }

    @InitBinder
    public void addBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getCode();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
