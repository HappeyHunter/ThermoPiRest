package com.dromree.thermopi.dbaccess.data.authorisedtokens;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DB Entity for AuthorisedTokens
 *
 */
@Document(collection = "AuthorisedTokens")
public class AuthorisedToken {

    @Id
    private String id;

    private String user;
    @Indexed
    private String token;

    public AuthorisedToken() {}

    public AuthorisedToken(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
