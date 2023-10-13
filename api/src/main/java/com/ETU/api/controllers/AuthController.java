package com.etu.api.controllers;

import com.etu.api.dtos.JwtRequest;
import com.etu.api.dtos.JwtResponse;
import com.etu.api.dtos.RegistrationUserDto;
import com.etu.api.dtos.RegistrationUserResponse;
import com.etu.api.exceptions.ErrorDto;
import com.etu.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "auth-controller")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Authorization", description = "Get authorization token")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = JwtResponse.class)))
    @ApiResponse(responseCode = "401", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Registration", description = "Create new user and get token")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = RegistrationUserResponse.class)))
    @ApiResponse(responseCode = "400", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDto registrationRequest){
        return authService.createUser(registrationRequest);
    }

    @GetMapping("/test")
    public String test(@PathParam("user") String user){
        return "Redirect is successful";
    }
}
