package ru.otus.springframework09.exception;

public class AuthorAlreadyExistsException extends RuntimeException{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
