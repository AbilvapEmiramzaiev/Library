package com.library.springboot.controllers;

import com.library.springboot.library_classes.Book;
import com.library.springboot.library_classes.Reader;
import com.library.springboot.services.BookService;
import com.library.springboot.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class WorkerController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private BookService bookService;
    @GetMapping
    public String home(Model model){

        List<Reader> readers = readerService.findAll();
        int readersCount = readers.size();
        List<Reader> youngReaders = readers.stream()
                .filter(reader -> reader.getAge() < 20)
                .collect(Collectors.toList());
        int youngReadersCount = youngReaders.size();
        model.addAttribute("readersCount", readersCount);
        model.addAttribute("youngReadersCount", youngReadersCount);

        List<Book> books2Copy = new ArrayList<>();
        for(Book book: bookService.findAllBooks()){
            if(book.getReader() != null && bookService.getNumbersOfCopies(book) <= 2){
                books2Copy.add(book);
            }
        }
        model.addAttribute("books2Copy", books2Copy);
        return "home";
    }
    @GetMapping("/findBookByPressmark")
    public String findBookByPressmark(@RequestParam("searchType") String searchType,
                                      @RequestParam("searchValue") String searchValue,
                                      Model model){
        List<Book> books = new ArrayList<>();
        if ("pressmark".equals(searchType)) {
            books.add(bookService.findByPressmark(searchValue));
        } else {
            books = bookService.findByName(searchValue);
        }
        model.addAttribute("books", books);
        home(model);
        return "home";
    }
}
