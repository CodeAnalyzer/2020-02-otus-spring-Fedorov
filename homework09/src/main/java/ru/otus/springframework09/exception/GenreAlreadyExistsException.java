package ru.otus.springframework09.exception;

public class GenreAlreadyExistsException extends RuntimeException{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
