package ru.otus.spring04.shell;

import lombok.RequiredArgsConstructor;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import ru.otus.spring04.service.ExamService;

@ShellComponent
@RequiredArgsConstructor
public class ExamShell {

    private int isStudentInfo;
    private int isAnswer;

    private final ExamService exam;

    @ShellMethod(value = "Testing student", key = {"test", "t"})
    public void testing() {
        exam.testing();
    }

    @ShellMethod(value = "Ask student information", key = {"name", "n"})
    public void askStudentName() {
        exam.askStudentName();
        isStudentInfo = 1;
    }

    @ShellMethod(value = "Ask question", key = {"question", "q"})
    @ShellMethodAvailability(value = "isAskQuestionAvailable")
    public void askQuestion() {
        exam.askQuestion();
        isAnswer = 1;
    }

    private Availability isAskQuestionAvailable() {
        return isStudentInfo == 0? Availability.unavailable("Сначала надо ввести ФИО"): Availability.available();
    }


    @ShellMethod(value = "Print result", key = {"result", "r"})
    @ShellMethodAvailability(value = "isPrintResultAvailable")
    public void printResult() {
        exam.printResult();
    }

    private Availability isPrintResultAvailable() {
        if (isStudentInfo == 0){
            return Availability.unavailable("Сначала надо ввести ФИО и ответить на вопросы");
        }
        else
        if (isAnswer == 0){
            return  Availability.unavailable("Сначала надо ответить на вопросы");
        }
        else
          return  Availability.available();
    }

}
