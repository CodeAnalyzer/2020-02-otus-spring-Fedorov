package ru.otus.springframework08.exception;

public class GenreAlreadyUsedException extends RuntimeException{
    public GenreAlreadyUsedException(String message) {
        super(message);
    }
}
