package ru.otus.springframework12.exception;

public class GenreNotFoundException extends RuntimeException{
    public GenreNotFoundException(String message) {
        super(message);
    }
}
