package ru.otus.springframework10.exception;

public class GenreAlreadyExistsException extends RuntimeException{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
