package ru.otus.springframework05.exception;

public class GenreAlreadyExistsException extends Exception{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
