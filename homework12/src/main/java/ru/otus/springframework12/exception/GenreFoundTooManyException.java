package ru.otus.springframework12.exception;

public class GenreFoundTooManyException extends RuntimeException{
    public GenreFoundTooManyException(String message) {
        super(message);
    }
}
