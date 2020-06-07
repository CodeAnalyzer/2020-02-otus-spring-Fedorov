package ru.otus.springframework09.rest;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.springframework09.domain.Author;
import ru.otus.springframework09.domain.Book;
import ru.otus.springframework09.domain.Genre;
import ru.otus.springframework09.service.BookWebService;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
@DisplayName("Контроллер  для работы с книгами ")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookWebService bookWebService;

    @Test
    @DisplayName("должен корректно возвращать страницу редактирования книги")
    public void testEditPage() throws Exception {
        Book book = new Book(1L, "Tom Sawyer",
                new Author(1L, "Mark Twain"),
                new Genre(1L, "Novel"));

        given(bookWebService.findById(any())).willReturn(book);

        mockMvc.perform(get("/books/edit_form/1")).
                andExpect(status().isOk()).
                andExpect(model().attribute("book", book));
    }

}
