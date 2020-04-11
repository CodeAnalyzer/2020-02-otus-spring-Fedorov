package ru.otus.spring04.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест команд ExamShell ")
@SpringBootTest
public class ExamShellTest {

    @Autowired
    private Shell shell;

    @DisplayName(" должен возвращать CommandNotCurrentlyAvailable если не ввели имя")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnCommandNotCurrentlyAvailableObjectWhenUserDoesNotLogin() {
        Object res =  shell.evaluate(() -> "question");
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);

        res =  shell.evaluate(() -> "result");
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }
}
