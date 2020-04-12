package ru.otus.springframework05.exception;

public class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
