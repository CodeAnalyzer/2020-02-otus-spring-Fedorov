package ru.otus.springframework07.exception;

public class GenreAlreadyExistsException extends Exception{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
