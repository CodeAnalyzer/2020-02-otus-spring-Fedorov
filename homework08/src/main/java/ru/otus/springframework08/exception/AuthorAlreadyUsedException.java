package ru.otus.springframework08.exception;

public class AuthorAlreadyUsedException extends RuntimeException{
    public AuthorAlreadyUsedException(String message) {
        super(message);
    }
}
