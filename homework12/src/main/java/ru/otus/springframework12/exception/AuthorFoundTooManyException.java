package ru.otus.springframework12.exception;

public class AuthorFoundTooManyException extends RuntimeException{
    public AuthorFoundTooManyException(String message) {
        super(message);
    }
}
