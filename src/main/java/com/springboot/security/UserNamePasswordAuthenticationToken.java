package com.springboot.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserNamePasswordAuthenticationToken implements Authentication {

    private static final long serialVersionUID = 1L;
    private Authentication authentication;
    private UserDetails userDetails;

    public UserNamePasswordAuthenticationToken(Authentication authentication, UserDetails userDetails){
        this.authentication = authentication;
        this.userDetails = userDetails;
    }

    @Override
    public String getName() {
        return authentication.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authentication.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return authentication.getDetails();
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }
}