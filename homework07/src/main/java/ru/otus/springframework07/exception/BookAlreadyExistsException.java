package ru.otus.springframework07.exception;

public class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
