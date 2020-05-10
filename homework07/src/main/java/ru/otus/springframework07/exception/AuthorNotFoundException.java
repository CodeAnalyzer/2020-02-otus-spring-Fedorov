package ru.otus.springframework07.exception;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
