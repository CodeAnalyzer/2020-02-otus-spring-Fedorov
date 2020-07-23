package ru.otus.springframework16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import ru.otus.springframework16.domain.Book;
import ru.otus.springframework16.service.BookWebService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookWebService bookWebService;

    @Autowired
    public BookController(BookWebService bookWebService){
        this.bookWebService = bookWebService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookWebService.findAll();
        model.addAttribute("books", books);

        String pageName = "";

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("USER"))) {
            pageName = "index";
        }
        else{
            pageName = "error";
        }

        return pageName;
    }

    @GetMapping("/books/add_form")
    public String showAddForm(Book book) {
        return "add_book";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        bookWebService.insert(book.getAuthor().getName(), book.getGenre().getName(), book.getTitle());
        return "redirect:/";
    }

    @GetMapping("/books/edit_form/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        Book book = bookWebService.findById(id);
        model.addAttribute("book", book);

        String pageName = "";

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("EDITOR"))) {
            pageName = "update_book";
        }
        else{
            pageName = "error";
        }

        return pageName;
    }

    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setBookID(id);
        bookWebService.update(book);
        return "redirect:/";
    }

    @GetMapping("/books/delete_form/{id}")
    public String deletePage(@PathVariable Long id, Model model) {
        Book book = bookWebService.findById(id);
        model.addAttribute("book", book);

        String pageName = "";

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ERASER"))) {
            pageName = "delete_book";
        }
        else{
            pageName = "error";
        }

        return pageName;
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        bookWebService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @ExceptionHandler()
    public ModelAndView handleNPE(NullPointerException e) {
        ModelAndView modelAndView = new ModelAndView("err500");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
