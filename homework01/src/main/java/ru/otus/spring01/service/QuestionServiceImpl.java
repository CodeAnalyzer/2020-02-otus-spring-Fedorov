package ru.otus.spring01.service;

import ru.otus.spring01.dao.QuestionDao;
import ru.otus.spring01.domain.Question;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao2) {
        this.dao = dao2;
    }
    public Question getByNumber(int number) {
        return dao.findByNumber(number);
    }
    public int getCountQuestion() {
        return dao.getCountQuestion();
    }
}
