package com.example.collecciisety;

public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(String str) {
        super(str);
    }
}
