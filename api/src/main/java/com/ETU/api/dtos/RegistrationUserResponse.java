package com.etu.api.dtos;

public class RegistrationUserResponse {
    private Integer user_id;
    private String firstname;
    private String lastname;
    private String email;

    private String token;

    public RegistrationUserResponse(Integer user_id, String firstname, String lastname, String email, String token) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.token = token;
    }

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
