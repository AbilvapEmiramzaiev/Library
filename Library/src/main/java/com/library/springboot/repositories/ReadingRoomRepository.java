package com.library.springboot.repositories;

import com.library.springboot.library_classes.ReadingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRoomRepository extends JpaRepository<ReadingRoom,Integer> {
}
