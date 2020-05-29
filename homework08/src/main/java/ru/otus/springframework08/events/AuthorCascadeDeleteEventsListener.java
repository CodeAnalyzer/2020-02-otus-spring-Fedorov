package ru.otus.springframework08.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import ru.otus.springframework08.domain.Author;
import ru.otus.springframework08.domain.Book;
import ru.otus.springframework08.exception.AuthorAlreadyUsedException;
import ru.otus.springframework08.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {

    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete (BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        val authorID = event.getSource().get("_id").toString();
        List<Book> bookList = bookRepository.findByAuthorID(authorID);
        if (!bookList.isEmpty()){
            throw new AuthorAlreadyUsedException("Автор уже используется и не может быть удален!");
        }
    }
}
