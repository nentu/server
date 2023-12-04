package com.example.server;

import com.example.server.exception.BadCredentialsException;
import com.example.server.exception.UnauthorizedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ExceptionsAdvice {

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(BadCredentialsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UnauthorizedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String unauthorizedUserHandler(UnauthorizedUserException ex) {
        return ex.getMessage();
    }
}