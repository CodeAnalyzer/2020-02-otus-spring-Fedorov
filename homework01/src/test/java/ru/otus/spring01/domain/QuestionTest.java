package ru.otus.spring01.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Question")
public class QuestionTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() throws IOException {
        String[] answers = new String[3];
        answers[0] = "answer";
        answers[1] = "answer";
        answers[2] = "answer";

        Question question = new Question("exam", answers, 1);

        assertEquals("exam", question.getTopic());
        assertEquals("answer", question.getPossibleAnswer()[0]);
        assertEquals(1, question.getRightAnswer());
    }

}
