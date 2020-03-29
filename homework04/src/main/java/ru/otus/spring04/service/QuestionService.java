package ru.otus.spring04.service;

import ru.otus.spring04.domain.Question;

public interface QuestionService {

    Question getByNumber(int number);
    int getCountQuestion();
}
