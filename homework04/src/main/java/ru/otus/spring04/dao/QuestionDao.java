package ru.otus.spring04.dao;

import ru.otus.spring04.domain.Question;

public interface QuestionDao {

    Question findByNumber(int number);
    int getCountQuestion();
}
