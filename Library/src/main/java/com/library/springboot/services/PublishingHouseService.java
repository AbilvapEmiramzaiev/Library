package com.library.springboot.services;

import com.library.springboot.library_classes.PublishingHouse;
import com.library.springboot.repositories.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishingHouseService {
    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    public PublishingHouse findByName(String name){return publishingHouseRepository.findPublishingHouseByName(name);}
    public void addPublishingHouse(PublishingHouse publishingHouse){ publishingHouseRepository.save(publishingHouse);}
}
