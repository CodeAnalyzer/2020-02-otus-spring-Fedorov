package ru.otus.springframework10.restcontroller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ru.otus.springframework10.domain.Author;
import ru.otus.springframework10.domain.Book;
import ru.otus.springframework10.domain.Genre;
import ru.otus.springframework10.service.BookWebService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookRestController.class)
@AutoConfigureMockMvc
@DisplayName("BookRestController для работы с книгами ")
public class BookRestControllerTest {

    private static final String DEFAULT_BOOK_TITLE  = "Tom Sawyer";
    private static final String DEFAULT_BOOK_AUTHOR = "Mark Twain";
    private static final String DEFAULT_BOOK_GENRE  = "Novel";

    @Autowired
    private BookRestController controller;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookWebService bookWebService;

    @Test
    @DisplayName("должен корректно возвращать страницу")
    public void testEditPage() throws Exception {

        Book book = new Book(1L, DEFAULT_BOOK_TITLE,
                new Author(1L, DEFAULT_BOOK_AUTHOR),
                new Genre(1L, DEFAULT_BOOK_GENRE));

        Mockito.when(bookWebService.findAll()).thenReturn(Collections.singletonList(book));

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", Matchers.is(DEFAULT_BOOK_TITLE)))
                .andExpect(jsonPath("$[0].author.name",Matchers.is(DEFAULT_BOOK_AUTHOR)))
                .andExpect(jsonPath("$[0].genre.name",Matchers.is(DEFAULT_BOOK_GENRE)));

    }
}
