package ru.otus.springframework08.events;

import lombok.RequiredArgsConstructor;

import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import ru.otus.springframework08.domain.Book;
import ru.otus.springframework08.domain.Genre;
import ru.otus.springframework08.exception.GenreAlreadyUsedException;
import ru.otus.springframework08.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreCascadeDeleteEventsListener extends AbstractMongoEventListener<Genre> {

    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete (BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        val genreID = event.getSource().get("_id").toString();
        List<Book> bookList = bookRepository.findByGenreID(genreID);
        if (!bookList.isEmpty()){
            throw new GenreAlreadyUsedException("Жанр уже используется и не может быть удален!");
        }
    }
}
