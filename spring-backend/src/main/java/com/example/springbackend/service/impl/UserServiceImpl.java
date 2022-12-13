package com.example.springbackend.service.impl;

import com.example.springbackend.model.User;
import com.example.springbackend.repository.UserRepository;
import com.example.springbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }


    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

}