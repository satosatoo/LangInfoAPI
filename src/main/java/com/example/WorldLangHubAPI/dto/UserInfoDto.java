package com.example.WorldLangHubAPI.dto;

import com.example.WorldLangHubAPI.models.Resource;
import com.example.WorldLangHubAPI.models.Role;

import java.util.List;

public class UserInfoDto {
    private int userId;
    private String username;
    private String password;
    private List<String> authorities;
    private List<String> resourceNames;

    public UserInfoDto() {
    }

    public UserInfoDto(int userId, String username, String password, List<String> authorities, List<String> resourceNames) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.resourceNames = resourceNames;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getResourceNames() {
        return resourceNames;
    }

    public void setResourceNames(List<String> resourceNames) {
        this.resourceNames = resourceNames;
    }
}
