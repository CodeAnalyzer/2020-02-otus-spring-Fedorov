package ru.otus.springframework12.service;

import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final Scanner scanner;

    public InputOutputServiceImpl(){
        this.scanner = new Scanner(System.in);
    }

    public void printOut(String st) {
        System.out.println(st);
    }

    public String readString() {
        return scanner.nextLine();
    }

    public Long readLong() {
        Long value = 0L;
        try{
            value = scanner.nextLong();
        } catch (InputMismatchException e) {
            printOut("Ошибка ввода числового значения!");
            value = -1L;
        }
        return value;
    }
}
