package ru.otus.springframework05.exception;

public class AuthorAlreadyExistsException extends Exception{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
