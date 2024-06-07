package com.ETU.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "JWT response")
public class JwtResponse {
    @Schema(type = "string", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiam9obm1hZ2x5QGdtYWlsLmNvbSIsImlhdCI6MTY5NzEzMjAxNCwiZXhwIjoxNjk3MTM1NjE0fQ.cKjlgvZXOrg_98TCWoa-2aezcNS57bAex5udeknl9CE")
    private String token;

    public JwtResponse() {
    }
    public JwtResponse(String token) {
        this.token = token;
    }
}
