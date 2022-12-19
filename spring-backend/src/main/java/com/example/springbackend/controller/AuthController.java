package com.example.springbackend.controller;


import com.example.springbackend.data.dto.AuthorizationDTO;
import com.example.springbackend.exception.AlreadyExistsException;
import com.example.springbackend.exception.BadRequestException;
import com.example.springbackend.exception.NotFoundException;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.User;
import com.example.springbackend.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<Response<Object>> authorize(@RequestBody @NonNull AuthorizationDTO req) {
        if (this.userService.findByLogin(req.getLogin()) == null)
            throw new AlreadyExistsException("User with login: " + req.getLogin() + " already exists");
        this.userService.saveUser(new User(req.getLogin(),
                req.getPassword(),
                req.getPhone(),
                req.getEmail(),
                Boolean.parseBoolean(req.getIsEmployee())));
        return Response.success();
    }

    @GetMapping("/log")
    public ResponseEntity<Response<Object>> authenticate(HttpServletRequest request) {
        User user;
        if ((user = this.userService.findByLogin(request.getHeader("login"))) != null)
            if (user.getLogin().equals(request.getHeader("password")))
                if (user.isEmployee()) return Response.success(true);
                else return Response.success(false);
            else throw new BadRequestException("Incorrect password");
        else throw new NotFoundException("User with login: " + request.getHeader("login") + " not found");
    }
}
