package ru.otus.spring03.service;

import ru.otus.spring03.domain.Question;
import ru.otus.spring03.domain.Student;

public interface ProcessService {

    Student askStudentName();
    boolean askQuestion(Question question, int number);
    void printResult(String name, int rightAnswer) ;
}
