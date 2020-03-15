package ru.otus.spring02.service;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.context.MessageSource;

import ru.otus.spring02.domain.Student;

@DisplayName("Класс ProcessServiceImpl")
class ProcessServiceImplTest {

    @Test
    @DisplayName("askStudentName")
    void askStudentNameTest() {
        InputOutputService ioMock = mock(InputOutputService.class);
        MessageSource messageMock = mock(MessageSource.class);
        ProcessService processService = new ProcessServiceImpl(ioMock, messageMock);
        Mockito.when(ioMock.readString()).thenReturn("Федоров");
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());

        Mockito.when(messageMock.getMessage(Mockito.anyString(), new Object[]{Mockito.any()}, Mockito.any(Locale.class))).thenReturn("ФИО студента");

        Student student = processService.askStudentName();
        assertThat(student)
                .hasFieldOrPropertyWithValue("name", "Федоров");
    }

    @Test
    @DisplayName("askQuestion")
    void askQuestionTest() {
    }

    @Test
    @DisplayName("printResult")
    void printResultTest() {
    }
}