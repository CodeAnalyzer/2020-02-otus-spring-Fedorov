package ru.otus.springframework06.exception;

public class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
