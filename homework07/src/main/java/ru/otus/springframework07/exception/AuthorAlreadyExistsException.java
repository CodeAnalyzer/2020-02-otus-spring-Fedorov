package ru.otus.springframework07.exception;

public class AuthorAlreadyExistsException extends Exception{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
