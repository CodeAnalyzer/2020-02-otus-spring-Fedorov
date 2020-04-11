package ru.otus.spring04.domain;

import java.io.IOException;

public class Question {

    public final String topic;
    private final String[] possibleAnswer;
    private final int rightAnswer;

    public Question(String topic, String[] possibleAnswer, int rightAnswer) throws  IOException {
        if ((topic == null)|(possibleAnswer[2] == null)|(rightAnswer==0)){
            throw new IOException();
        }
        this.topic = topic;
        this.possibleAnswer = possibleAnswer;
        this.rightAnswer = rightAnswer;
    }

    public String getTopic() {
        return topic;
    }

    public String[] getPossibleAnswer() {
        return possibleAnswer;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }
}
