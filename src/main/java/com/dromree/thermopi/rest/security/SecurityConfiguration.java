package com.dromree.thermopi.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthorisedFilter authorisedFilter;

    /**
     * Configures the security settings.
     * Secure paths can only be accessed from the current network
     * Adds the Authorisation filter to all other requests
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/ThermoPi/Secure/**", "/ThermoPi/CurrentTemperature")
                    .access("hasIpAddress('127.0.0.1') or hasIpAddress('::1') or hasIpAddress('192.168.1.0/24')")
                    .and()
                    .antMatcher("/ThermoPi/**")
                    .addFilterBefore(authorisedFilter, BasicAuthenticationFilter.class);
    }

}
