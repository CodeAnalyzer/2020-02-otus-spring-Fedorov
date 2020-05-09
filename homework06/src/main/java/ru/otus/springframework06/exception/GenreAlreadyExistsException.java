package ru.otus.springframework06.exception;

public class GenreAlreadyExistsException extends Exception{
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
