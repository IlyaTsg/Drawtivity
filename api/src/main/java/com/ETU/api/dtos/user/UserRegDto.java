package com.etu.api.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Registration user request")
public class UserRegDto {
    @Schema(type = "string", example = "testFName")
    private String firstname;
    @Schema(type = "string", example = "testLName")
    private String lastname;
    @Schema(type = "string", example = "test@test.com")
    private String email;
    @Schema(type = "string", example = "testPass")
    private String password;

    public UserRegDto(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
