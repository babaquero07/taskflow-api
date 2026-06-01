package com.taskflow.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.net.URI;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 1. Catch your actual custom service exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setProperty("timestamp", Instant.now());

        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
    }

    // 2. Catch validation errors (and extract the actual validation messages)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ex.getBody();
        problemDetail.setStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Failed");
        problemDetail.setDetail("One or more fields failed validation.");

        // Optional: Extract specific field errors to show the client what failed
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        problemDetail.setProperty("errors", errors);

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }
}