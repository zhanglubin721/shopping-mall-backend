package com.zhanglubin.shoppingmallbackend.domain;

import java.util.List;

/**
 * @author zhanglubin
 * @date 2021/2/20
 */
public class TokenResult {

    private String active;
    private String exp;
    private String user_name;
    private List<String> authorities;
    private String client_id;
    private List<String> scope;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public TokenResult(String active, String exp, String user_name, List<String> authorities, String client_id, List<String> scope) {
        this.active = active;
        this.exp = exp;
        this.user_name = user_name;
        this.authorities = authorities;
        this.client_id = client_id;
        this.scope = scope;
    }

    public TokenResult() {
    }
}
