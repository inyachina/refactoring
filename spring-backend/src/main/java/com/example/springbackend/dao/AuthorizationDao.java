package com.example.springbackend.dao;

import lombok.Data;

@Data
public class AuthorizationDao {
    private String login;
    private String password;
    private String email;
    private String phone;
    private String isEmployee;
}
