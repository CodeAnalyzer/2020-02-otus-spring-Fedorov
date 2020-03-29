package ru.otus.spring04.service;

import ru.otus.spring04.domain.Question;
import ru.otus.spring04.domain.Student;

public interface ProcessService {

    Student askStudentName();
    boolean askQuestion(Question question, int number);
    void printResult(String name, int rightAnswer) ;
}
