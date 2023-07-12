package com.library.springboot.controllers;

import com.library.springboot.library_classes.Book;
import com.library.springboot.library_classes.Reader;
import com.library.springboot.library_classes.ReadingRoom;
import com.library.springboot.services.BookService;
import com.library.springboot.services.ReaderService;
import com.library.springboot.services.ReadingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/readers")
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReadingRoomService readingRoomService;

    @GetMapping
    public String findAll(Model model){
        Iterable<Reader> readers = readerService.findAll();
        Iterable<ReadingRoom> readingRooms = readingRoomService.findAll();
        model.addAttribute("readers", readers);
        model.addAttribute("readingRooms", readingRooms);
        return "readers";
    }
    @GetMapping("/filter")
    public String filterReaders(Model model,
                                @RequestParam(value = "filterType", required = false) String filterType,
                                @RequestParam(value = "filterValue", required = false) String filterValue) {
        List<Reader> readers = readerService.findAll();
        if (filterType != null && filterValue != "") {
            switch (filterType) {
                case "age":
                    int ageFilter = Integer.parseInt(filterValue);
                    readers = readers.stream()
                            .filter(reader -> reader.getAge() == ageFilter)
                            .collect(Collectors.toList());
                    break;
                case "name":
                    readers = readers.stream()
                            .filter(reader -> reader.getSurname().contains(filterValue))
                            .collect(Collectors.toList());
                    break;
            }
        }
        Iterable<ReadingRoom> readingRooms = readingRoomService.findAll();
        model.addAttribute("readingRooms", readingRooms);
        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("/assignBook")
    public String getBooksReaders(Model model){
        Iterable<Reader> readers = readerService.findAll();
        Iterable<Book> books = bookService.findUnAssigned();
        model.addAttribute("books", books);
        model.addAttribute("readers", readers);
        return "book-management";
    }
    @PostMapping("/returnBooks")
    public String returnBooks(@RequestParam("readerIdreturn")Integer readerId){
        Reader reader = readerService.findById(readerId);
        List<Book> readerBooks = reader.getBooks();
        for(Book book : readerBooks){
            book.setReader(null);
            book.setDateOfAssigning(null);
            bookService.update(book);
        }
        reader.getBooks().clear();
        readerService.save(reader);
        return "redirect:/readers/assignBook";
    }
    @PostMapping("/assignBook")
    public String assignBook(@RequestParam("readerId") Integer readerId,
                            @RequestParam("selectedBooks") List<Integer> booksId){
        List<Book> books = new ArrayList<Book>();
        Reader reader = readerService.findById(readerId);
        for(Integer bookID : booksId){
            Book book = bookService.findById(bookID);
            book.setReader(reader);
            book.setDateOfAssigning(LocalDate.now());
            books.add(book);
        }
        if(reader.getBooks() == null){
            reader.setBooks(new ArrayList<Book>());
        }
        reader.setBooks(books);
        readerService.save(reader);
        return "redirect:/readers/assignBook";
    }
    @PostMapping
    public String addReader(@ModelAttribute Reader reader,
                            Model model,
                            @RequestParam("readingRoomId") Integer readingRoomId) {
        ReadingRoom readingRoom = readingRoomService.findById(readingRoomId);
        if(readingRoom != null && readingRoom.hasPlace()){
            if (readingRoom.getReaders() == null) {readingRoom.setReaders(new ArrayList<>());}
            reader.setReadingRoom(readingRoom);
            readerService.save(reader);
        }else {
            model.addAttribute("roomName", readingRoom.getName());
            return "redirect:/NoAvailablePlacesInReadingRoom"; }
        return "redirect:/readers";
    }
}
