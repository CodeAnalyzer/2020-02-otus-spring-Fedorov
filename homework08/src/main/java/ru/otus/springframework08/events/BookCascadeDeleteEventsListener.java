package ru.otus.springframework08.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.springframework08.domain.Book;
import ru.otus.springframework08.domain.Comment;
import ru.otus.springframework08.repository.CommentRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onBeforeDelete (BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        val bookID = event.getSource().get("_id").toString();
        List<Comment> commentList = commentRepository.findByBookID(bookID);
        if (!commentList.isEmpty()){
            commentRepository.deleteAll(commentList);
        }
    }
}
