package ru.otus.spring03.service;

import ru.otus.spring03.domain.Question;

public interface QuestionService {

    Question getByNumber(int number);
    int getCountQuestion();
}
