package ru.otus.springframework05.exception;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
