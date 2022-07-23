package com.example.collecciisety;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotEntered extends RuntimeException {
    public EmployeeNotEntered(String s) {
        super(s);
    }
}
