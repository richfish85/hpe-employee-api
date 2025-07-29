package com.example.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String id) {
        super("Employee not found: " + id);
    }
}
