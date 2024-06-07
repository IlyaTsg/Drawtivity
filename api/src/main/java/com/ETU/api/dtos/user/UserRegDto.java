package com.ETU.api.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Ilya Tsygankov
 * @created 12.03.2024
 */
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
}
