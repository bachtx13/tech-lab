package com.sem4project.sem4.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = -7783613182359714469L;

    public OAuth2AuthenticationProcessingException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}
