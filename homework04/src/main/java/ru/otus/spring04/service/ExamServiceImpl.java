package ru.otus.spring04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.spring04.domain.Student;

@Service
public class ExamServiceImpl implements ExamService {

    private Student student;
    private final QuestionService questionService;
    private final ProcessService processService;
    private int res;

    @Autowired
    public ExamServiceImpl(QuestionService questionService, ProcessService processService, Student student) {
        this.student = student;
        this.questionService = questionService;
        this.processService = processService;
    }

    public ExamServiceImpl() {
        this.student = null;
        this.questionService = null;
        this.processService = null;
    }

    public void testing() {
        this.student = processService.askStudentName();
        for (int i = 0; i < questionService.getCountQuestion(); i++) {
            if (processService.askQuestion(questionService.getByNumber(i), i + 1)) {
                this.res++;
            }
        }
        processService.printResult(this.student.getName(), this.res);
    }
    
    public void askStudentName() {
        this.student = processService.askStudentName();
    }

    public void askQuestion() {
        for (int i = 0; i < questionService.getCountQuestion(); i++){
            if (processService.askQuestion( questionService.getByNumber(i), i+1)) {
                this.res++;
            }
        }
    }

    public void printResult() {
       processService.printResult(this.student.getName(), this.res);
    }
}
