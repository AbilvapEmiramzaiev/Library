package com.library.springboot.services;

import com.library.springboot.library_classes.Worker;
import com.library.springboot.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        Worker worker = new Worker();
        worker.setUsername(username);
        worker.setPassword(hashedPassword);
        workerRepository.save(worker);
    }
}
