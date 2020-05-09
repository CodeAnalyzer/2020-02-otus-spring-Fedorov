package ru.otus.springframework06.exception;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
