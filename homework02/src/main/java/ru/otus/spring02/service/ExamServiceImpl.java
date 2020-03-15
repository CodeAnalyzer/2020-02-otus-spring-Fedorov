package ru.otus.spring02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring02.domain.Student;

@Service
public class ExamServiceImpl implements ExamService {

    private Student student;
    private QuestionService questionService;
    private ProcessService processService;

    public void setStudent(Student student) {
        this.student = student;
    }
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    public void setConsoleService(ProcessService processService) {
        this.processService = processService;
    }

    @Autowired
    public ExamServiceImpl(QuestionService questionService, ProcessService processService, Student student) {
        this.student = student;
        this.questionService = questionService;
        this.processService = processService;
    }

    public ExamServiceImpl() {
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
