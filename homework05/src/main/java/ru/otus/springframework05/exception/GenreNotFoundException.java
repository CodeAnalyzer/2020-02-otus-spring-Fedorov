package ru.otus.springframework05.exception;

public class GenreNotFoundException extends Exception{
    public GenreNotFoundException(String message) {
        super(message);
    }
}
