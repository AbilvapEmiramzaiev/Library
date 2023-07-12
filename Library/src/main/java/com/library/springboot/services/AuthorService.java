package com.library.springboot.services;

import com.library.springboot.library_classes.Author;
import com.library.springboot.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author findByName(String name){return authorRepository.findAuthorByName(name);}
    public void addAuthor(Author author){  authorRepository.save(author);}
}
