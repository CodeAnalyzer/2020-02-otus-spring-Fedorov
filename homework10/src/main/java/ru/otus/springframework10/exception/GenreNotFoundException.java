package ru.otus.springframework10.exception;

public class GenreNotFoundException extends RuntimeException{
    public GenreNotFoundException(String message) {
        super(message);
    }
}
