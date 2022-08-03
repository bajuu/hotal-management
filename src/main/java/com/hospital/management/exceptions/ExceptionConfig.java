package com.hospital.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleException(
            CustomException e) throws IOException {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", e.getLocalizedMessage());
        errorResponse.put("status", e.httpStatus.toString());
        return new ResponseEntity<>(errorResponse, e.httpStatus);
    }
}
