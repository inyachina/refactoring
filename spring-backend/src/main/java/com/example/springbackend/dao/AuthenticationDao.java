package com.example.springbackend.dao;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticationDao {
    @NonNull
    private String login;
    @NonNull
    private String password;
}
