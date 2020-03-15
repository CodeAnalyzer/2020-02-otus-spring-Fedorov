package ru.otus.spring02.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Student")
class StudentTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Student student = new Student("Ivan");
        assertEquals("Ivan", student.getName());
    }
}
