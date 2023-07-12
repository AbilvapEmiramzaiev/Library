package com.library.springboot.library_classes;

import com.querydsl.core.annotations.Generated;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Integer book_id;
    private String title;
    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    ) @Nullable
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "publishing_house_id")
    @Nullable
    private PublishingHouse publishingHouse;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "reader_id")
    private Reader reader;
    private Integer year;
    private String pressmark;
    @Nullable
    private LocalDate dateOfAssigning;

  }
