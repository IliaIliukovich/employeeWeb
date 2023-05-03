package com.telran.employeeweb.service;

import com.telran.employeeweb.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User saveUser(User user);

}
