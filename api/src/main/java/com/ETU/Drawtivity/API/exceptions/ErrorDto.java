package com.ETU.DemoApi.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDto {
    private Integer status;
    private String message;
    private Date timestamp;

    public ErrorDto(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
