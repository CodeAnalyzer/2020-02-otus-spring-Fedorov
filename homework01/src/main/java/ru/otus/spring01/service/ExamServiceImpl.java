package ru.otus.spring01.service;

import ru.otus.spring01.domain.Student;

public class ExamServiceImpl implements ExamService {

    private Student student;
    private QuestionService questionService;
    private ProcessService processService;

    public void setStudent (Student student){
        this.student = student;
    };
    public void setQuestionService (QuestionService questionService){
        this.questionService = questionService;
    };
    public void setConsoleService (ProcessService processService){
        this.processService = processService;
    };

    public ExamServiceImpl(QuestionService questionService, ProcessService processService, Student student) {
        this.student = student;
        this.questionService = questionService;
        this.processService = processService;
    }

    public ExamServiceImpl() {
    }

    public void testing() {
      this.student = processService.askName();
      int res = 0;
      for (int i = 0; i < questionService.getCountQuestion(); i++){
        if (processService.askQuestion( questionService.getByNumber(i), i+1)) {
            res++;
        }
      }
        processService.printResult(this.student.getName(), res);
    }
}
