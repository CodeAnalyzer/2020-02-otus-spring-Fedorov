package ru.otus.springframework08.exception;

public class GenreNotFoundException extends Exception{
    public GenreNotFoundException(String message) {
        super(message);
    }
}
