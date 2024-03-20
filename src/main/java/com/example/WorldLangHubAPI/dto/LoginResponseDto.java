package com.example.WorldLangHubAPI.dto;

public class LoginResponseDto {
    private UserInfoDto userInfoDto;
    private String jwt;


    public LoginResponseDto() {
    }

    public LoginResponseDto(UserInfoDto userInfoDto, String jwt) {
        this.userInfoDto = userInfoDto;
        this.jwt = jwt;
    }

    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }

    public void setUserInfoDto(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
