package com.ETU.api.controllers;

import com.ETU.api.dtos.user.UserDto;
import com.ETU.api.dtos.user.UserRoleUpdReq;
import com.ETU.api.exceptions.ErrorDto;
import com.ETU.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "user-controller", description = "Доступно только для пользователей с ролью ROLE_ADMIN. В дальнейшем некоторые маппинги буду доступны и ROLE_USER, например обновление собственных значений")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Get all users")
    @ApiResponse(responseCode = "200", content = @Content(
            array = @ArraySchema(schema = @Schema(implementation = UserDto.class))))
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/{id}/roles")
    @Operation(summary = "Обновление роли пользователя", description = "Обновление роли пользователя по id")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(example = "User roles updated successfully.")))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> updateUserRole(@PathVariable Integer id, @RequestBody UserRoleUpdReq userRoleUpdReq) {
        return userService.updateRoleByUserId(id, userRoleUpdReq);
    }
}
