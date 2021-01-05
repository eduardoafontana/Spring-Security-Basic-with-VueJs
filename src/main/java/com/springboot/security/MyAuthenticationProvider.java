package com.springboot.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyAuthenticationProvider implements AuthenticationProvider {

    public Authentication authenticate(Authentication authentication)  throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        if(!username.equals("user"))
            throw new AuthenticationExceptionImpl("User or password is invalid!");

        GrantedAuthority grant = new GrantedAuthorityImpl("ROLE_USER");

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        grantList.add(grant);

        UserDetails user = new User(username, "[protected]", true, true, true, true, grantList);

        return new UserNamePasswordAuthenticationToken(authentication, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}