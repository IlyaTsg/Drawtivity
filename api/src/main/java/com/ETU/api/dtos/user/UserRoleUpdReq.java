package com.ETU.api.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Запрос обновления роли пользователя")
public class UserRoleUpdReq {
    @Schema(example = "[\n\"ROLE_USER\"\n]")
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
