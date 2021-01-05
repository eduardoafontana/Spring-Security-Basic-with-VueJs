package com.springboot.security;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptionImpl extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public AuthenticationExceptionImpl(String msg) {
        super(msg);
    }

}