package com.academy.spring_lv4.exception;

public class EmailDuplicatedException extends RuntimeException{
    public EmailDuplicatedException(String message) {
        super(message);
    }
}
