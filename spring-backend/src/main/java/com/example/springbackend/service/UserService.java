package com.example.springbackend.service;

import com.example.springbackend.model.User;


public interface UserService {
    User findByLogin(String login);
    User saveUser(User user);
}
