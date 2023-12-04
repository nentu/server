package com.example.server.exception;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException() {
        super("Unauthorized user. Please sign in");
    }
}
