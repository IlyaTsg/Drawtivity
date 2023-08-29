package com.ETU.api.controllers;

import com.ETU.api.dtos.JwtRequest;
import com.ETU.api.dtos.RegistrationUserDto;
import com.ETU.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
