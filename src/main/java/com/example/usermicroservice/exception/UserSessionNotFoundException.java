package com.example.usermicroservice.exception;

public class UserSessionNotFoundException extends Exception {
    public UserSessionNotFoundException(String message) {
        super(message);
    }
}
