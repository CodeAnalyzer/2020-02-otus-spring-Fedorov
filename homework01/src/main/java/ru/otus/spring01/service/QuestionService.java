package ru.otus.spring01.service;

import ru.otus.spring01.domain.Question;

public interface QuestionService {

    Question getByNumber(int number);
    int getCountQuestion();
}
