package com.ETU.api.dtos.lti;

import lombok.Data;

/**
 * @author Ilya Tsygankov
 * @created 20.05.2023
 */
@Data
public class ToolRegRequest {
    private String endpoint;
    private String reg_key;
    private String reg_password;

    public ToolRegRequest(String endpoint, String reg_key, String reg_password) {
        this.endpoint = endpoint;
        this.reg_key = reg_key;
        this.reg_password = reg_password;
    }
}
