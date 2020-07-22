package ru.otus.springframework16.exception;

public class GenreAlreadyExistsException extends RuntimeException{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
