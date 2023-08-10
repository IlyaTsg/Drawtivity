package com.ETU.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int user_id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User(){};
    public User(int user_id, String firstname, String lastname, String email, String password)
    {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public int getUser_id(){ return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

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


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
