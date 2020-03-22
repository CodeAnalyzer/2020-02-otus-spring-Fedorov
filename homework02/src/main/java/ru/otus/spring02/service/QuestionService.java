package ru.otus.spring02.service;

import ru.otus.spring02.domain.Question;

public interface QuestionService {

    Question getByNumber(int number);
    int getCountQuestion();
}
