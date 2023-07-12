package com.library.springboot.services;

import com.library.springboot.library_classes.ReadingRoom;
import com.library.springboot.repositories.ReadingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingRoomService {
    @Autowired
    private ReadingRoomRepository readingRoomRepository;
    public ReadingRoom findById(Integer id){
        Optional<ReadingRoom> optionalReadingRoom = readingRoomRepository.findById(id);
        return optionalReadingRoom.orElseThrow(() -> new IllegalArgumentException("Reading room not found"));
    }
    public List<ReadingRoom> findAll(){return readingRoomRepository.findAll();}
}
