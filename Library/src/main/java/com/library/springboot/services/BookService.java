package com.library.springboot.services;

import com.library.springboot.library_classes.*;
import com.library.springboot.repositories.AuthorRepository;
import com.library.springboot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublishingHouseService publishingHouseService;
    public Book findById(Integer id) {
        Optional<Book> optBook = bookRepository.findById(id);
        return optBook.orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }
    public Integer getNumbersOfCopies(Book book){ return bookRepository.countBookByTitle(book.getTitle());}
    public List<Book> findByName(String title){return bookRepository.findBooksByTitle(title);}
    public Book findByPressmark(String pressmark){
        return bookRepository.findBookByPressmark(pressmark);
    }
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }
    public void deleteByName(String title){bookRepository.deleteBookByTitle(title);}
    public List<Book> findUnAssigned(){return  bookRepository.findBookByReaderIsNull();}
    public void update(Book book){ bookRepository.save(book);}
    public void addBook(String title, List<Author> authors, String publishingHouse, Integer year,String pressmark){
        List<Author> authorList = new ArrayList<>();
        for (Author author : authors){
            Author objA = authorService.findByName(author.getName());
            if(objA == null){authorService.addAuthor(author); }
            objA = authorService.findByName(author.getName());
            if(objA != null && objA.getBooks() == null){ objA.setBooks(new ArrayList<>());}
            authorList.add(objA);
        }
        PublishingHouse publishingHouseobj = publishingHouseService.findByName(publishingHouse);
        if(publishingHouseobj == null){
            PublishingHouse pbH = new PublishingHouse(publishingHouse);
            publishingHouseService.addPublishingHouse(pbH);
        }
        Book newBook = Book.builder()
                .title(title)
                .authors(authorList)
                .year(year)
                .pressmark(pressmark)
                .publishingHouse(publishingHouseService.findByName(publishingHouse))
                .build();
        for (Author author : authorList){
           author.getBooks().add(newBook);
        }
        bookRepository.save(newBook);
    }
    public  List<Book> findAllBooksWithPublishingHouse(){return bookRepository.findAllBooksWithPublishingHouse();}
}
