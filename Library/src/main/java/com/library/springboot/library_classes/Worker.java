package com.library.springboot.library_classes;

import com.library.springboot.user.IUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="worker")
public class Worker implements IUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer worker_id;
    private String username;
    private String password;
}
