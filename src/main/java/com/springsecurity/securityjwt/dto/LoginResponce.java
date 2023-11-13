package com.springsecurity.securityjwt.dto;

import com.springsecurity.securityjwt.entity.User;

public class LoginResponce {
    private User user;
    private String jwtToken;


    public LoginResponce(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
