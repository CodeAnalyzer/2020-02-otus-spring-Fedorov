package ru.otus.springframework07.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message) {
        super(message);
    }
}
