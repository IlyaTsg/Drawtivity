package com.etu.api.dtos;

import lombok.Data;

@Data
public class LtiRegistrationResponse {
    private String grant_type;
    private String client_assertion_type;
    private String client_assertion;
    private String redirect_uri;
    private String state;
    private String response_type;
    private String scope;
    private String login_hint;

    public LtiRegistrationResponse(String grant_type, String client_assertion_type, String client_assertion, String redirect_uri, String state, String response_type, String scope, String login_hint) {
        this.grant_type = grant_type;
        this.client_assertion_type = client_assertion_type;
        this.client_assertion = client_assertion;
        this.redirect_uri = redirect_uri;
        this.state = state;
        this.response_type = response_type;
        this.scope = scope;
        this.login_hint = login_hint;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_assertion_type() {
        return client_assertion_type;
    }

    public void setClient_assertion_type(String client_assertion_type) {
        this.client_assertion_type = client_assertion_type;
    }

    public String getClient_assertion() {
        return client_assertion;
    }

    public void setClient_assertion(String client_assertion) {
        this.client_assertion = client_assertion;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getLogin_hint() {
        return login_hint;
    }

    public void setLogin_hint(String login_hint) {
        this.login_hint = login_hint;
    }
}
