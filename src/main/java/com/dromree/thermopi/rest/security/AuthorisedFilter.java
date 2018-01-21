package com.dromree.thermopi.rest.security;

import com.dromree.thermopi.rest.data.AuthorisedTokenData;
import com.dromree.thermopi.services.AuthorisedTokensServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorisedFilter extends GenericFilterBean {

    private static String AUTHENTICATION_SCHEME = "BEARER ";
    private static String WWW_AUTHENTICATION_HEADER = AUTHENTICATION_SCHEME + "realm=ThermoPi";
    private static String SECURE_PATH = "SECURE";
    private static String CURRENT_TEMPERATURE = "CURRENTTEMPERATURE";
    private static String UNAUTHORISED_MESSAGE = "No token provided";
    private static String FORBIDDEN_MESSAGE = "Invalid token provided";

    private AuthorisedTokensServices authorisedTokensServices;

    public AuthorisedFilter() {
    }

    @Autowired
    public AuthorisedFilter(AuthorisedTokensServices authorisedTokensServices) {
        this.authorisedTokensServices = authorisedTokensServices;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getServletPath().toUpperCase();

        if (!path.contains(SECURE_PATH)
                && !path.contains(CURRENT_TEMPERATURE)) {
            String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

            if (!isValidAuthentication(authHeader)) {
                abortUnauthorised(httpResponse);
                return;
            } else if (!isValidToken(authHeader)) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, FORBIDDEN_MESSAGE);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isValidToken(String authHeader) {
        String[] splitAuth = authHeader.split(" ");
        AuthorisedTokenData token = null;

        if (splitAuth.length == 2) {
            token = authorisedTokensServices.getAuthorisedTokenByToken(splitAuth[1]);
        }

        return token != null && token.getToken().equals(splitAuth[1]);
    }

    private boolean isValidAuthentication(String authHeader) {
        return authHeader != null && authHeader.toUpperCase().startsWith(AUTHENTICATION_SCHEME) && authHeader.length() > AUTHENTICATION_SCHEME.length();
    }

    private void abortUnauthorised(HttpServletResponse httpResponse) throws IOException {
        httpResponse.setHeader(HttpHeaders.WWW_AUTHENTICATE, WWW_AUTHENTICATION_HEADER);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORISED_MESSAGE);
    }
}
