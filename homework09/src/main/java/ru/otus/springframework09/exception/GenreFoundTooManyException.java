package ru.otus.springframework09.exception;

public class GenreFoundTooManyException extends RuntimeException{
    public GenreFoundTooManyException(String message) {
        super(message);
    }
}
