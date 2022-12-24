package com.example.springbackend.service.impl;

import com.example.springbackend.data.dto.AuthenticationDTO;
import com.example.springbackend.data.dto.AuthorizationDTO;
import com.example.springbackend.exception.AlreadyExistsException;
import com.example.springbackend.exception.BadRequestException;
import com.example.springbackend.exception.NotFoundException;
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
    public User authenticate(AuthenticationDTO dto) {
        return this.userRepository.findByLogin(dto.getLogin()).map((user -> {
            if (user.getLogin().equals(dto.getPassword()))
                return user;
            else throw new BadRequestException("Incorrect password");
        })).orElseThrow(() -> new NotFoundException("User with login: " + dto.getLogin() + " not found"));
    }

    @Override
    public User authorize(AuthorizationDTO dto) {
        this.userRepository.findByLogin(dto.getLogin()).ifPresent((user) -> {
            throw new AlreadyExistsException("User with login: " + dto.getLogin() + " already exists");
        });

        return this.userRepository.save(new User(dto.getLogin(),
                dto.getPassword(),
                dto.getPhone(),
                dto.getEmail(),
                Boolean.parseBoolean(dto.getIsEmployee())));
    }

    @Override
    public User findByLogin(String login) {
        return this.userRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("User with login: " + login + " not found"));
    }
}
