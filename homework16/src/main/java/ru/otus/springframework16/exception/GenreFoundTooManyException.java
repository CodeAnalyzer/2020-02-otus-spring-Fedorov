package ru.otus.springframework16.exception;

public class GenreFoundTooManyException extends RuntimeException{
    public GenreFoundTooManyException(String message) {
        super(message);
    }
}
