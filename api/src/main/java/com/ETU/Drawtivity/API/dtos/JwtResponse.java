package com.ETU.DemoApi.dtos;

import lombok.Data;

@Data
public class JwtResponse {
    private String Token;

    public JwtResponse() {
    }
    public JwtResponse(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }
    public void setToken(String token) {
        Token = token;
    }
}
