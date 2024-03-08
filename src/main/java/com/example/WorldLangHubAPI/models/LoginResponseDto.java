package com.example.WorldLangHubAPI.models;

public class LoginResponseDto {
    private UserApplication userApplication;
    private String jwt;

    public LoginResponseDto(UserApplication userApplication, String jwt) {
        this.userApplication = userApplication;
        this.jwt = jwt;
    }

    public UserApplication getUserApplication() {
        return userApplication;
    }

    public void setUserApplication(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
