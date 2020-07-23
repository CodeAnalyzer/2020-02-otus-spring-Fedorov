package ru.otus.springframework16.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.springframework16.domain.Book;
import ru.otus.springframework16.repository.BookRepository;

import java.util.List;

@Component
public class BookRepositoryHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    public BookRepositoryHealthIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        boolean healthIsOk = true;

        try {
            List<Book> books = bookRepository.findAll();
        }
        catch (Exception e){
            healthIsOk = false;
        }

        if (healthIsOk) {
            return Health.up().
                    withDetail("message", "BookRepository работает без ошибок.").
                    build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Проблема с BookRepository: при поиске книг возникло исключение!")
                    .build();
        }
    }
}
