package com.example.springbackend.controller;


import com.example.springbackend.facade.ErrorBody;
import com.example.springbackend.facade.Response;
import com.example.springbackend.model.User;
import com.example.springbackend.dao.AuthorizationDao;
import com.example.springbackend.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<Response<Object>> authorize(@RequestBody @NonNull AuthorizationDao req) {
        try {
            System.out.println(req);
            this.userService.findByLogin(req.getLogin());
            this.userService.saveUser(new User(req.getLogin(),
                    req.getPassword(),
                    req.getPhone(),
                    req.getEmail(),
                    Boolean.valueOf(req.getIsEmployee())));
            return Response.success();
        } catch (Exception e) {
            return Response.failure(
                    Response.error(
                            new ErrorBody(e.getMessage(),
                                    HttpStatus.FORBIDDEN, Arrays.toString(e.getStackTrace()))));
        }
    }

    @GetMapping("/log")
    public ResponseEntity<Response<Object>> authenticate(HttpServletRequest request) {
        User user;
        if ((user = this.userService.findByLogin(request.getHeader("login"))) != null)
            if (user.getLogin().equals(request.getHeader("password")))
                if(user.isEmployee()) return Response.success(true);
                else return Response.success(false);
        return Response.failure(
                Response.error(
                        new ErrorBody("Неверно указан логин или пароль.",
                                HttpStatus.NOT_FOUND)));
    }

}
