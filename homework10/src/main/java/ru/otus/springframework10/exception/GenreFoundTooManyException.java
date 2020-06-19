package ru.otus.springframework10.exception;

public class GenreFoundTooManyException extends RuntimeException{
    public GenreFoundTooManyException(String message) {
        super(message);
    }
}
