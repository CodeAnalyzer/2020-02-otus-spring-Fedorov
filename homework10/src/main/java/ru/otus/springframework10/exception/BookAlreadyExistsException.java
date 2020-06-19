package ru.otus.springframework10.exception;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
