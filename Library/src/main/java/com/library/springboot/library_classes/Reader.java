package com.library.springboot.library_classes;

import com.library.springboot.user.IUser;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Reader")
public class Reader implements IUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reader_id;
    private Integer readingCard;
    private String surname;
    private Integer passport_id;
    private LocalDate dateOfBirth;
    private String address;
    private Integer age;
    private String phone;
    private String education;
    private String scienceDegree;
    @OneToMany(mappedBy = "reader")
    @Nullable
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "reading_room_id")
    @Nullable
    private ReadingRoom readingRoom;

}
