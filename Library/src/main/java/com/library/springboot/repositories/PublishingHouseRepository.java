package com.library.springboot.repositories;

import com.library.springboot.library_classes.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse,Integer> {

    PublishingHouse save(PublishingHouse publishingHouse);
    PublishingHouse findPublishingHouseByName(String name);
}
