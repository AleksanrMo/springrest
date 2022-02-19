package com.rest.exception;

public class NoSuchEmployeeException extends RuntimeException {

    public NoSuchEmployeeException(String st) {
        super(st);
    }
}
