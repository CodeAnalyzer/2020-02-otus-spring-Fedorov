package ru.otus.springframework16.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "editor")
    private Long editor;

    @Column(name = "eraser")
    private Long eraser;
}
