package ru.otus.springframework12.exception;

public class GenreAlreadyExistsException extends RuntimeException{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
