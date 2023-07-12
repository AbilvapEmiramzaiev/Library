package com.library.springboot.repositories;

import com.library.springboot.library_classes.Book;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {
    public List<Book> findAll();
    Book save(Book book);
    Integer countBookByTitle(String title);
    List<Book> findBooksByTitle(String title);
    Book findBookByPressmark(String pressmark);
    List<Book>  findBookByReaderIsNull();
    void deleteBookByTitle(String title);
}
