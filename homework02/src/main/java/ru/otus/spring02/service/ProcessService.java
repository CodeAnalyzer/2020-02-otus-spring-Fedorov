package ru.otus.spring02.service;

import ru.otus.spring02.domain.Question;
import ru.otus.spring02.domain.Student;

public interface ProcessService {

    Student askStudentName();
    boolean askQuestion(Question question, int number);
    void printResult(String name, int rightAnswer) ;
}
