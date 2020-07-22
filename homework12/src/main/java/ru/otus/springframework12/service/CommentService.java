package ru.otus.springframework12.service;

public interface CommentService {
    void insert();
    void update();
    void delete();
    void findByBook();
    boolean ServiceAvailable();
}
