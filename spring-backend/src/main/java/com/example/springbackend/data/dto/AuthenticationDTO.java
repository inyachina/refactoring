package com.example.springbackend.data.dto;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class AuthenticationDTO {
    private String login;
    private String password;
}
