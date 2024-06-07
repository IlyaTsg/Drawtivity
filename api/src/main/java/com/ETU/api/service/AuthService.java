package com.ETU.api.service;

import com.ETU.api.dtos.JwtRequest;
import com.ETU.api.dtos.user.UserRegDto;
import com.ETU.api.dtos.user.UserRegResp;
import com.ETU.api.entities.Role;
import com.ETU.api.entities.User;
import com.ETU.api.exceptions.ErrorDto;
import com.ETU.api.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Ilya Tsygankov
 * @created 17.08.2023
 */
@Service
public class AuthService {
    private final DetailsService detailsService;
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(DetailsService detailsService, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager, UserService userService) {
        this.detailsService = detailsService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.UNAUTHORIZED.value(), "Email or password are incorrect"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = detailsService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        User user = userService.getUser(authRequest.getEmail());

        return ResponseEntity.ok(new UserRegResp(user.getUser_id(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRoles().stream().map(Role::getName).toList(), token));
    }

    public ResponseEntity<?> createUser(@RequestBody UserRegDto registrationRequest){
        // Проверка на существование пользователя
        // Лучше перенести в UserService
        if(detailsService.findByEmail(registrationRequest.getEmail()).isPresent()){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), "User already exists"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.addUser(registrationRequest);
        UserDetails userDetails = detailsService.loadUserByUsername(user.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new UserRegResp(user.getUser_id(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRoles().stream().map(Role::getName).toList(), token));
    }
}
