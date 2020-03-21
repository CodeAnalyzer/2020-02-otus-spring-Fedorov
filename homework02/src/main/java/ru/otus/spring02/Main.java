package ru.otus.spring02;

import org.springframework.context.annotation.*;
import ru.otus.spring02.service.ExamService;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ExamService exam = context.getBean(ExamService.class);
        exam.testing();
    }
}
