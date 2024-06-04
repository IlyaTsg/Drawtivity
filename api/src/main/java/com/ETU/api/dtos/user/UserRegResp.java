package com.etu.api.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Registration user response")
public class UserRegResp {
    @Schema(type = "int", example = "35")
    private Integer user_id;
    @Schema(type = "string", example = "testFName")
    private String firstname;
    @Schema(type = "string", example = "testLName")
    private String lastname;
    @Schema(type = "string", example = "test@test.com")
    private String email;
    @Schema(example = "[\n\"ROLE_USER\"\n]")
    private List<String> roles;
    @Schema(type = "string", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiam9obm1hZ2x5QGdtYWlsLmNvbSIsImlhdCI6MTY5NzEzMjAxNCwiZXhwIjoxNjk3MTM1NjE0fQ.cKjlgvZXOrg_98TCWoa-2aezcNS57bAex5udeknl9CE")
    private String token;

    public UserRegResp(Integer user_id, String firstname, String lastname, String email, List<String> roles, String token) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }
}
