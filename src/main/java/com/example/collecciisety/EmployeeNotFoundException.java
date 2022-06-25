package com.example.collecciisety;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String string) {
        super(string);
    }
}
