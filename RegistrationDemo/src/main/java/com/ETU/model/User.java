package com.ETU.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int userNo;
    private String name;
    private String country;

    public User(){};
    public User(int userNo, String name, String country)
    {
        this.userNo = userNo;
        this.name = name;
        this.country = country;
    }

    public int getUserNo() { return userNo; }
    public void setUserNo(int userNo) { this.userNo = userNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
