package ru.otus.spring04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.spring04.dao.QuestionDao;
import ru.otus.spring04.domain.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    @Autowired
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
