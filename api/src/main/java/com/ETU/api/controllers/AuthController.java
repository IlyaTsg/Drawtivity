package com.etu.api.controllers;

import com.etu.api.dtos.JwtRequest;
import com.etu.api.dtos.RegistrationUserDto;
import com.etu.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.websocket.server.PathParam;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDto registrationRequest){
        return authService.createUser(registrationRequest);
    }

    @GetMapping("/test")
    public String test(@PathParam("user") String user){
        return "Redirect is successful";
    }
}
