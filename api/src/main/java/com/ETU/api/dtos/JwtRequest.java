package com.etu.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "JWT request")
public class JwtRequest {
    @Schema(title = "email", type = "string", example = "test@test.com")
    private String email;
    @Schema(title = "password", type = "string", example = "test_pass")
    private String password;

    public JwtRequest() {
    }
    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
