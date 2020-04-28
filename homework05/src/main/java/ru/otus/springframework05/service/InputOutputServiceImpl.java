package ru.otus.springframework05.service;

import org.springframework.stereotype.Service;
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
        return scanner.nextLong();
    }
}
