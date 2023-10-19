package com.etu.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Registration user response")
public class RegistrationUserResponse {
    @Schema(type = "int", example = "35")
    private Integer user_id;
    @Schema(type = "string", example = "testFName")
    private String firstname;
    @Schema(type = "string", example = "testLName")
    private String lastname;
    @Schema(type = "string", example = "test@test.com")
    private String email;
    @Schema(type = "string", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiam9obm1hZ2x5QGdtYWlsLmNvbSIsImlhdCI6MTY5NzEzMjAxNCwiZXhwIjoxNjk3MTM1NjE0fQ.cKjlgvZXOrg_98TCWoa-2aezcNS57bAex5udeknl9CE")
    private String token;

    public RegistrationUserResponse(Integer user_id, String firstname, String lastname, String email, String token) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.token = token;
    }

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
