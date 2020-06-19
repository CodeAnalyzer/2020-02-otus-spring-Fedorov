package ru.otus.springframework10.exception;

public class AuthorFoundTooManyException extends RuntimeException{
    public AuthorFoundTooManyException(String message) {
        super(message);
    }
}
