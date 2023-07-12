package com.library.springboot.library_classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Entity
@Table(name = "Publishing_house")
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publishing_house_id")
    private Integer publishingHouse_id;
    private String name;
    public PublishingHouse(String name){ this.name = name;}
    public void setPublishingHouse_id(Integer publishingHouse_id) {
        this.publishingHouse_id = publishingHouse_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Integer getPublishingHouse_id() {
        return publishingHouse_id;
    }

    @OneToMany(mappedBy = "publishingHouse")
    private List<Book> books;
}
