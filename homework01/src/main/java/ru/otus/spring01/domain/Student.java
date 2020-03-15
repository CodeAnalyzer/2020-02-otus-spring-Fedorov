package ru.otus.spring01.domain;

public class Student {

    private String name;

    public Student() {
        this.name = "";
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {return this.name; }
}
