package ru.otus.springframework08.exception;

public class AuthorAlreadyExistsException extends Exception{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
