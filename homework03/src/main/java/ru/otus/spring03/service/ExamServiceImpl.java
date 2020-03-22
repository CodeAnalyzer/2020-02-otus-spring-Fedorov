package ru.otus.spring03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring03.domain.Student;

@Service
public class ExamServiceImpl implements ExamService {

    private Student student;
    private final QuestionService questionService;
    private final ProcessService processService;

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
        int res = 0;
        for (int i = 0; i < questionService.getCountQuestion(); i++) {
            if (processService.askQuestion(questionService.getByNumber(i), i + 1)) {
                res++;
            }
        }
        processService.printResult(this.student.getName(), res);
    }
}
