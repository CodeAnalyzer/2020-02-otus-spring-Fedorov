package ru.otus.spring03.dao;

import ru.otus.spring03.domain.Question;

public interface QuestionDao {

    Question findByNumber(int number);
    int getCountQuestion();
}
