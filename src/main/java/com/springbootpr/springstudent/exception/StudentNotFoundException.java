package com.springbootpr.springstudent.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {

        super(message);
    }
}
