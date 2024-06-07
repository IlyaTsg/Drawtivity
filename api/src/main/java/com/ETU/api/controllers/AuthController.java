package com.ETU.api.controllers;

import com.ETU.api.dtos.JwtRequest;
import com.ETU.api.dtos.user.UserRegDto;
import com.ETU.api.dtos.user.UserRegResp;
import com.ETU.api.exceptions.ErrorDto;
import com.ETU.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(summary = "Authorization", description = "Get authorization token")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = UserRegResp.class)))
    @ApiResponse(responseCode = "401", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    @Operation(summary = "Registration", description = "Create new user and get token")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = UserRegResp.class)))
    @ApiResponse(responseCode = "400", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createUser(@RequestBody UserRegDto registrationRequest){
        return authService.createUser(registrationRequest);
    }

    @GetMapping("/test")
    public String test(@PathParam("user") String user){
        return "Redirect is successful";
    }
}
