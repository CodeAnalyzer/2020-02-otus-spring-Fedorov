package ru.otus.spring02.dao;

import ru.otus.spring02.domain.Question;

public interface QuestionDao {

    Question findByNumber(int number);
    int getCountQuestion();
}
