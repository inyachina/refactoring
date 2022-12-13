package com.example.springbackend.data.dto;

import lombok.Data;

@Data
public class AuthorizationDTO extends AuthenticationDTO {
    private String email;
    private String phone;
    private String isEmployee;
}
