package ru.otus.springframework16.shell;

import lombok.RequiredArgsConstructor;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.springframework16.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentShell {

    private final CommentService commentService;

    private Availability isServiceAvailable () {
        return commentService.ServiceAvailable() == false ? Availability.unavailable("Сначала следует добавить книгу в БД"): Availability.available();
    }

    @ShellMethod(value = "Comment insert", key = {"Comment insert", "ci"})
    @ShellMethodAvailability(value = "isServiceAvailable")
    public void commentInsert() {
        commentService.insert();
    }

    @ShellMethod(value = "Comment update", key = {"Comment update", "cu"})
    @ShellMethodAvailability(value = "isServiceAvailable")
    public void commentUpdate() {
        commentService.update();
    }

    @ShellMethod(value = "Comment delete", key = {"Comment delete", "cd"})
    @ShellMethodAvailability(value = "isServiceAvailable")
    public void commentDelete() {
        commentService.delete();
    }

    @ShellMethod(value = "Comment find all for book", key = {"Comment find all for book", "cb"})
    @ShellMethodAvailability(value = "isServiceAvailable")
    public void commentFindAllForBook() {
        commentService.findByBook();
    }

}
