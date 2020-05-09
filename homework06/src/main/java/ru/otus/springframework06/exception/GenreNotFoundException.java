package ru.otus.springframework06.exception;

public class GenreNotFoundException extends Exception{
    public GenreNotFoundException(String message) {
        super(message);
    }
}
