package com.library.springboot.services;

import com.library.springboot.library_classes.Reader;
import com.library.springboot.library_classes.Worker;
import com.library.springboot.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public Reader findById(Integer id){
        Optional<Reader> optReader = readerRepository.findById(id);
        return optReader.orElseThrow(() -> new IllegalArgumentException("Reader not found"));
    }
    public List<Reader> findAll(){return readerRepository.findAll();}
    public void save(Reader reader){
        readerRepository.save(reader);
    }
}
