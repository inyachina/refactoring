package com.example.springbackend.service;

import com.example.springbackend.data.dto.AuthenticationDTO;
import com.example.springbackend.data.dto.AuthorizationDTO;
import com.example.springbackend.model.User;


public interface UserService {
    User authenticate(AuthenticationDTO dto);

    User authorize(AuthorizationDTO dto);

    User findByLogin(String login);
}
