package ru.otus.springframework10.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.otus.springframework10.domain.Book;
import ru.otus.springframework10.service.BookWebService;

import java.util.List;

@RestController
public class BookRestController {

    private final BookWebService bookWebService;

    @Autowired
    public BookRestController(BookWebService bookWebService){
        this.bookWebService = bookWebService;
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        List<Book> listBook = bookWebService.findAll();
        return listBook;
    }

    @GetMapping("/books/{id}")
    public Book findById(@PathVariable("id") Long id) {
        Book book = bookWebService.findById(id);
        return book;
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookWebService.insert(book.getAuthor().getName(), book.getGenre().getName(), book.getTitle());
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) {
        return bookWebService.update(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookWebService.delete(id);
    }

}
