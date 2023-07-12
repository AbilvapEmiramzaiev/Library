package com.library.springboot.services;

import com.library.springboot.library_classes.Worker;
import com.library.springboot.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WorkerDetailsService  implements UserDetailsService {
    @Autowired
    private WorkerRepository workerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Worker worker = workerRepository.findWorkerByUsername(username);
        if (worker == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(worker.getUsername(), worker.getPassword(), new ArrayList<>());
    }

}
