package ru.otus.spring02.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.spring02.domain.Question;
import ru.otus.spring02.domain.Student;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Value("${lang.locale}")
    private Locale locale;

    private final MessageSource messageSource;
    private final InputOutputService ioService;

    @Autowired
    public ProcessServiceImpl (InputOutputService ioService, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    public Student askStudentName () {
        ioService.printOut(messageSource.getMessage("exam.name",null, locale));
        return new Student(ioService.readString());
    }

    public boolean askQuestion( Question question, int number){
        ioService.printOut(messageSource.getMessage("exam.question",new String [] {String.valueOf(number)}, locale));
        ioService.printOut(question.topic);

        for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            ioService.printOut(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
      }

      int answer = ioService.readInt();
      return (answer == question.getRightAnswer());
   }

    public void printResult(String name, int rightAnswer) {
        ioService.printOut(messageSource.getMessage("exam.name",null, locale));
        ioService.printOut(name);
        ioService.printOut(messageSource.getMessage("exam.result",null, locale));
        ioService.printOut(String.valueOf(rightAnswer));
    }
}
