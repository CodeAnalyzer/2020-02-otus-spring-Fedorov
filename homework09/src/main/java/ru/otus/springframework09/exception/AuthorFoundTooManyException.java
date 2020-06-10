package ru.otus.springframework09.exception;

public class AuthorFoundTooManyException extends RuntimeException{
    public AuthorFoundTooManyException(String message) {
        super(message);
    }
}
