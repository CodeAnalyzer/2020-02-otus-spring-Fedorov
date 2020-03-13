package ru.otus.spring01.dao;

import ru.otus.spring01.domain.Question;

public interface QuestionDao {

    Question findByNumber(int number);
    int getCountQuestion();
}
