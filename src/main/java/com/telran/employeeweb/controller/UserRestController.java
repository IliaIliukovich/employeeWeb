package com.telran.employeeweb.controller;

import com.telran.employeeweb.model.entity.User;
import com.telran.employeeweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersRest")
public class UserRestController {

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
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

}
