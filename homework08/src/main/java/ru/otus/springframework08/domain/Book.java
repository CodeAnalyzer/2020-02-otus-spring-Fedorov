package ru.otus.springframework08.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Book")
public class Book {

    @Id
    private String bookID;

    @Field(name = "title")
    private String title;

    @Field(name = "author")
    private Author author;

    @Field(name = "genre")
    private Genre genre;

}
