package com.example.server.exception;

import com.example.server.storage.dto.UserDTO;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(UserDTO user) {
        super("Could not find user: " + user);
    }
}
