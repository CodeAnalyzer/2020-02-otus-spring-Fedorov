package ru.otus.springframework07.exception;

public class GenreNotFoundException extends Exception{
    public GenreNotFoundException(String message) {
        super(message);
    }
}
