package com.ETU.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int userNo;
    private String firstname;
    private String lastname;
    private String email;
    private String number;

    public User(){};
    public User(int userNo, String firstname, String lastname, String email, String number)
    {
        this.userNo = userNo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
    }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
