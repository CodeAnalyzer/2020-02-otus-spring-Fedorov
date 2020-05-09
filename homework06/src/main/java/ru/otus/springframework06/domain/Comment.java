package ru.otus.springframework06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comment")
@NamedEntityGraph(name = "CommentsWithBook",
        attributeNodes = {@NamedAttributeNode(value = "book")})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bookID")
    private Book book;

    @Column(name = "remark", nullable = false)
    private String remark;

}
