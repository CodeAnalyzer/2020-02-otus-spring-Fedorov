package ru.otus.springframework16.exception;

public class AuthorFoundTooManyException extends RuntimeException{
    public AuthorFoundTooManyException(String message) {
        super(message);
    }
}
