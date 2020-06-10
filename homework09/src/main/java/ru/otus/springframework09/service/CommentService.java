package ru.otus.springframework09.service;

public interface CommentService {
    void insert();
    void update();
    void delete();
    void findByBook();
    boolean ServiceAvailable();
}
