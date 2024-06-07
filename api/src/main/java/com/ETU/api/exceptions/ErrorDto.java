package com.ETU.api.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Error response")
public class ErrorDto {
    @Schema(type = "int", example = "error_status")
    private Integer status;
    @Schema(type = "string", example = "Error message")
    private String message;
    @Schema(type = "string", example = "2023-10-12T18:40:37.477+00:00")
    private Date timestamp;

    public ErrorDto(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
