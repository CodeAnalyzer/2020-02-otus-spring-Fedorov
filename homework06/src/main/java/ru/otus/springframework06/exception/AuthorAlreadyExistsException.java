package ru.otus.springframework06.exception;

public class AuthorAlreadyExistsException extends Exception{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
