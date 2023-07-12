package com.library.springboot.controllers;

import com.library.springboot.library_classes.Author;
import com.library.springboot.library_classes.Book;
import com.library.springboot.library_classes.PublishingHouse;
import com.library.springboot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String findAll(Model model){
        Iterable<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
    @PostMapping("/delete")
    @Transactional
    public String deleteBook(@RequestParam("deltitle") String deltitle) {
        // Delete the book by its ID
        bookService.deleteByName(deltitle);
        return "redirect:/books";
    }
    @PostMapping
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("authors") String authors,
                          @RequestParam("publicationYear") Integer publicationYear,
                          @RequestParam("pressmark") String pressmark,
                          @RequestParam("publicationHouse") String publishingHouseN) {

        List<String> authorNames = Arrays.asList(authors.split(","));
        List<Author> authorList = new ArrayList<>();
        for (String authorName : authorNames) {
            Author author = Author.builder().name(authorName).build();
            authorList.add(author);
        }
        bookService.addBook(title,authorList,publishingHouseN, publicationYear,pressmark);
        return "redirect:/books";
    }
}
