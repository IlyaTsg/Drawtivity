package com.ETU.api.dtos.user;

import com.ETU.api.entities.Role;
import com.ETU.api.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Ilya Tsygankov
 * @created 12.03.2024
 */
@Data
@Schema(description = "Схема пользователя")
public class UserDto {
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

    public UserDto(Integer user_id, String firstname, String lastname, String email, List<String> roles, String token) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.roles = roles;
    }

    public UserDto(User user) {
        this.user_id = user.getUser_id();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(Role::getName).toList();
    }
}
