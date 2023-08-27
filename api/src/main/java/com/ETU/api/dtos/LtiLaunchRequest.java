package com.ETU.api.dtos;

import lombok.Data;

@Data
public class LtiLaunchRequest {
    private String issuer; // "https://moodle.example.com"
    private String login_hint; // "user123" или "user@example.com"
    private String target_link_uri; // "http://localhost:3000/task"
    private String lti_message_hint; // "Launch demo task"
    private String deployment_id; // Уникальный id использования(развертывания) приложения"deployment123"
    private String roles; // Пока что "test"
    private String context; // "cource123"
    private String tool_consumer_instance_guid; // уникальный id LMS "moodle_instance123"
    private String token_id; // для аутентификации запроса, пока что "test"

    public LtiLaunchRequest(String issuer, String login_hint, String target_link_uri, String lti_message_hint, String deployment_id, String roles, String context, String tool_consumer_instance_guid, String token_id) {
        this.issuer = issuer;
        this.login_hint = login_hint;
        this.target_link_uri = target_link_uri;
        this.lti_message_hint = lti_message_hint;
        this.deployment_id = deployment_id;
        this.roles = roles;
        this.context = context;
        this.tool_consumer_instance_guid = tool_consumer_instance_guid;
        this.token_id = token_id;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getLogin_hint() {
        return login_hint;
    }

    public void setLogin_hint(String login_hint) {
        this.login_hint = login_hint;
    }

    public String getTarget_link_uri() {
        return target_link_uri;
    }

    public void setTarget_link_uri(String target_link_uri) {
        this.target_link_uri = target_link_uri;
    }

    public String getLti_message_hint() {
        return lti_message_hint;
    }

    public void setLti_message_hint(String lti_message_hint) {
        this.lti_message_hint = lti_message_hint;
    }

    public String getDeployment_id() {
        return deployment_id;
    }

    public void setDeployment_id(String deployment_id) {
        this.deployment_id = deployment_id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTool_consumer_instance_guid() {
        return tool_consumer_instance_guid;
    }

    public void setTool_consumer_instance_guid(String tool_consumer_instance_guid) {
        this.tool_consumer_instance_guid = tool_consumer_instance_guid;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }
}
