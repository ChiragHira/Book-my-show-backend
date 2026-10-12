package com.chirag.bookmyshowbackend.service;

public class ProfileConflictException  extends RuntimeException{
    public ProfileConflictException(String message) {
        super(message);
    }
}
