

package com.oec.department.exception;
 
import java.time.LocalDateTime;

import java.util.LinkedHashMap;

import java.util.Map;
 
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
 
@RestControllerAdvice

public class GlobalExceptionHandler {
 
    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<Map<String, Object>> handleNotFound(

            ResourceNotFoundException ex) {
 
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());

    }
 
    @ExceptionHandler(DuplicateResourceException.class)

    public ResponseEntity<Map<String, Object>> handleDuplicate(

            DuplicateResourceException ex) {
 
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());

    }
 
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Map<String, Object>> handleValidation(

            MethodArgumentNotValidException ex) {
 
        Map<String, String> errors = new LinkedHashMap<>();
 
        ex.getBindingResult().getFieldErrors().forEach(error ->

            errors.putIfAbsent(

                error.getField(), error.getDefaultMessage()));
 
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());

        body.put("status", 400);

        body.put("error", "Validation failed");

        body.put("details", errors);
 
        return ResponseEntity.badRequest().body(body);

    }
 
    @ExceptionHandler(DataIntegrityViolationException.class)

    public ResponseEntity<Map<String, Object>> handleDatabaseConflict(

            DataIntegrityViolationException ex) {
 
        return buildResponse(

            HttpStatus.CONFLICT,

            "A department with this name already exists");

    }
 
    @ExceptionHandler(Exception.class)

    public ResponseEntity<Map<String, Object>> handleOtherExceptions(

            Exception ex) {
 
        return buildResponse(

            HttpStatus.INTERNAL_SERVER_ERROR,

            "An unexpected server error occurred");

    }
 
    private ResponseEntity<Map<String, Object>> buildResponse(

            HttpStatus status, String message) {
 
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());

        body.put("status", status.value());

        body.put("error", status.getReasonPhrase());

        body.put("message", message);
 
        return ResponseEntity.status(status).body(body);

    }

}

 