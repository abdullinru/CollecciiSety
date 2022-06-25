package com.example.collecciisety;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String str) {
        super(str);
    }
}
