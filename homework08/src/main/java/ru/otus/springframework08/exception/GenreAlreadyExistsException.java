package ru.otus.springframework08.exception;

public class GenreAlreadyExistsException extends Exception{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
