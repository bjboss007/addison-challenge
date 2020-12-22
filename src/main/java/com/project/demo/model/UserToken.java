package com.project.demo.model;

public class UserToken {
    private String token;

    public UserToken() {
    }

    public UserToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
