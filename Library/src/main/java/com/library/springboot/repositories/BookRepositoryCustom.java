package com.library.springboot.repositories;

import com.library.springboot.library_classes.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepositoryCustom {
     List<Book> findAllBooksWithPublishingHouse();
}
