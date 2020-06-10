package ru.otus.springframework09.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework09.domain.Book;
import ru.otus.springframework09.domain.Comment;
import ru.otus.springframework09.repository.BookRepository;
import ru.otus.springframework09.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final ShellService shellService;
    private final MessageService messageService;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository, ShellService shellService, MessageService messageService) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public void insert() {
        Long bookID = shellService.bookIDInput();
        Optional<Book> book = bookRepository.findById(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        String remark = shellService.commentRemarkInput();
        commentRepository.save(new Comment(0L, book.get(), remark));
    }

    @Override
    @Transactional
    public void update() {
        Long bookID = shellService.bookIDInput();
        Optional<Book> book = bookRepository.findById(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        List<Comment> list = commentRepository.findByBookID(bookID);
        shellService.commentListOutput(list);
        Long commentID = shellService.commentIDInput();
        Optional<Comment> comment = commentRepository.findById(commentID);
        if (!comment.isPresent()){
            messageService.messagePrintOut("comment.error.remarkNotFound");
            return;
        }
        String remark = shellService.commentRemarkInput();
        comment.get().setRemark(remark);
        commentRepository.save(comment.get());
    }

    @Override
    @Transactional
    public void delete() {
        Long bookID = shellService.bookIDInput();
        Optional<Book> book = bookRepository.findById(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        List<Comment> list = commentRepository.findByBookID(bookID);
        shellService.commentListOutput(list);
        Long commentID = shellService.commentIDInput();
        Optional<Comment> comment = commentRepository.findById(commentID);
        if (!comment.isPresent()){
            messageService.messagePrintOut("comment.error.remarkNotFound");
            return;
        }
        commentRepository.delete(comment.get());
    }

    @Override
    @Transactional(readOnly = true)
    public void findByBook() {
        Long bookID = shellService.bookIDInput();
        Optional<Book> book = bookRepository.findById(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        List<Comment> list = commentRepository.findByBookID(bookID);
        shellService.commentListOutput(list);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean ServiceAvailable() {
        return bookRepository.count() > 0;
    }
}
