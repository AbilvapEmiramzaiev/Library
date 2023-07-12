package com.library.springboot.repositories;

import com.library.springboot.library_classes.Book;
import com.library.springboot.library_classes.QBook;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.library.springboot.library_classes.QPublishingHouse.publishingHouse;

public class BookRepositoryImpl implements BookRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAllBooksWithPublishingHouse() {
        JPAQuery<Book> query = new JPAQuery<Book>(em);
        QBook book = QBook.book;
        return query.from(book)
                .innerJoin(book.publishingHouse, publishingHouse)
                .fetchJoin()
                .fetch();
    }
}
