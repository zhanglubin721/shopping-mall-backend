package com.zhanglubin.shoppingmallbackend.domain;

/**
 * @author zhanglubin
 * @date 2021/2/22
 */
public class ResourcesUser {

    private String username;
    private String password;

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

    public ResourcesUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ResourcesUser() {
    }
}
