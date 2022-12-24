package com.example.springbackend.controller;


import com.example.springbackend.data.dto.AuthenticationDTO;
import com.example.springbackend.data.dto.AuthorizationDTO;
import com.example.springbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity authorize(@RequestBody @NonNull AuthorizationDTO dto) {
        return ResponseEntity.ok(this.userService.authorize(dto));
    }

    @GetMapping("/log")
    public ResponseEntity authenticate(@RequestBody @NonNull AuthenticationDTO dto) {
        return ResponseEntity.ok(this.userService.authenticate(dto));

    }
}
