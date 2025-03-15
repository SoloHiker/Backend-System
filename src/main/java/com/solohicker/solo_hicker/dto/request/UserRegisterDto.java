package com.solohicker.solo_hicker.dto.request;

public class UserRegisterDto {
    private String username;
    private String password;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
