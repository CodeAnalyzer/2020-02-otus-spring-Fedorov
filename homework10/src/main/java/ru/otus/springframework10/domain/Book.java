package ru.otus.springframework10.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
@NamedEntityGraph(name = "BookWithAuthorAndGenre",
        attributeNodes = {@NamedAttributeNode(value = "author"),
                          @NamedAttributeNode(value = "genre")})
public class Book {

    @Id
    @JsonProperty("bookID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    @Column(name = "title", nullable = false, unique = true)
    @JsonProperty("title")
    private String title;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "authorID")
    @JsonProperty("author")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "genreID")
    @JsonProperty("genre")
    private Genre genre;

}
