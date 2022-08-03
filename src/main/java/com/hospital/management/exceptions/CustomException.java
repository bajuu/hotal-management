package com.hospital.management.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    HttpStatus httpStatus;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
