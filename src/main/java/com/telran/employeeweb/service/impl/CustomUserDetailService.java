package com.telran.employeeweb.service.impl;

import com.telran.employeeweb.model.entity.User;
import com.telran.employeeweb.repository.UserRepository;
import com.telran.employeeweb.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserService, UserDetailsService {

    private UserRepository repository;

    public CustomUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
