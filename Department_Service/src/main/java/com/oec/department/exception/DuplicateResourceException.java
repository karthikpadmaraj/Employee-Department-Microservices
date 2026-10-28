package com.oec.department.exception;
 
public class DuplicateResourceException extends RuntimeException {
 
    public DuplicateResourceException(String message) {
        super(message);
    }
}