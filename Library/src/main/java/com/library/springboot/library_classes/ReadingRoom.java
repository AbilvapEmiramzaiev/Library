package com.library.springboot.library_classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "reading_room")
@Getter
@Setter
public class ReadingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reading_room_id;
    private Integer number;
    private String name;
    private Integer capacity;
    @OneToMany(mappedBy = "readingRoom")
    private List<Reader> readers;
    public boolean hasPlace(){return readers.size() < capacity;}
}
