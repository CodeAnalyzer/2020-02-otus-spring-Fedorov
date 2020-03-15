package ru.otus.spring01.service;

import java.util.Scanner;
import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Student;

public class ProcessServiceImpl implements ProcessService {

    private Scanner scanner;

    public ProcessServiceImpl(Scanner scanner){
        this.scanner = scanner;
    }

    public ProcessServiceImpl(){
        this.scanner = new Scanner(System.in);
    }

    public Student askName() {
      System.out.print("Введите ФИО: ");
      return new Student(scanner.nextLine());
    }

    public boolean askQuestion( Question question, int number){
      System.out.println("Вопрос №: " + String.valueOf(number));
      System.out.println(question.topic);

      for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            System.out.println(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
      }

      int answer = scanner.nextInt();
      return (answer == question.getRightAnswer());
   }

    public void printResult(String name, int rightAnswer) {
        System.out.println("ФИО студента:");
        System.out.println(name);
        System.out.println("Количество правильных ответов:");
        System.out.println(String.valueOf(rightAnswer));
    }

}
