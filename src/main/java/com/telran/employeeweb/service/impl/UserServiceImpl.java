package com.telran.employeeweb.service.impl;

import com.telran.employeeweb.model.entity.User;
import com.telran.employeeweb.repository.UserRepository;
import com.telran.employeeweb.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public User saveUser(User user){
        return repository.save(user);
    }

}
