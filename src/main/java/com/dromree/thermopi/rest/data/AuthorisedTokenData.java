package com.dromree.thermopi.rest.data;

import javax.validation.constraints.NotBlank;

/**
 * Network side data object for Authorised Tokens
 */
public class AuthorisedTokenData {

    @NotBlank(message = "User required")
    private String user;
    @NotBlank(message = "Token required")
    private String token;

    public AuthorisedTokenData() {}

    public AuthorisedTokenData(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
