package com.library.springboot.library_classes;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    private Integer author_id;


    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
    private  String  name;
}
