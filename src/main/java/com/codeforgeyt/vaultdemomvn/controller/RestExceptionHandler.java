package com.codeforgeyt.vaultdemomvn.controller;

import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BadCredentialsException.class, SignatureException.class})
    public String handlerJWTException(BadCredentialsException e){
        return e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        return e.getMessage();
    }
}
