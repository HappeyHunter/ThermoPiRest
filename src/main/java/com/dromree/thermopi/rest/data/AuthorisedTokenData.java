package com.dromree.thermopi.rest.data;

/**
 * Network side data object for Authorised Tokens
 */
public class AuthorisedTokenData {

    private String token;
    private String user;

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
