package com.library.springboot.repositories;

import com.library.springboot.library_classes.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    Worker findWorkerByUsername(String username);
}
