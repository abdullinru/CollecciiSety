package com.example.collecciisety;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String str) {
        super(str);
    }
}
