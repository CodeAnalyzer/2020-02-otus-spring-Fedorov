package ru.otus.spring01.service;

import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Student;

public interface ProcessService {

    Student askName();
    boolean askQuestion(Question question, int number);
    void printResult(String name, int rightAnswer) ;
}
