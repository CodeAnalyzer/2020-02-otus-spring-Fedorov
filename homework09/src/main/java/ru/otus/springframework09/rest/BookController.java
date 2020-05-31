package ru.otus.springframework09.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.otus.springframework09.domain.Book;
import ru.otus.springframework09.service.AuthorService;
import ru.otus.springframework09.service.BookWebService;
import ru.otus.springframework09.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookWebService bookWebService;
    private final AuthorService authorService;
    private final GenreService genreService;


    @Autowired
    public BookController(BookWebService bookWebService, AuthorService authorService, GenreService genreService){
        this.bookWebService = bookWebService;
        this.authorService  = authorService;
        this.genreService   = genreService;
    }

    @GetMapping("/Add_Book")
    public String showAddForm(Book book) {
        return "add_book";
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookWebService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        bookWebService.insert(book.getAuthor().getName(), book.getGenre().getName(), book.getTitle());
        return "redirect:/";
    }

    @GetMapping("/books/update/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        Book book = bookWebService.findById(id);
        model.addAttribute("book", book);
        return "Update_Book";
    }

    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        bookWebService.update(book);
        return "redirect:/";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        bookWebService.delete(id);
        return "redirect:/";
    }

}
